package com.mohaberabi.pokemoncompose.core.data.source.local

import com.mohaberabi.pokemoncompose.core.data.database.dao.PokemonDao
import com.mohaberabi.pokemoncompose.core.data.database.dao.PokemonInfoDao
import com.mohaberabi.pokemoncompose.core.data.database.mapper.toPokemon
import com.mohaberabi.pokemoncompose.core.data.database.mapper.toPokemonEntity
import com.mohaberabi.pokemoncompose.core.data.database.mapper.toPokemonInfo
import com.mohaberabi.pokemoncompose.core.data.database.mapper.toPokemonInfoEntity
import com.mohaberabi.pokemoncompose.core.domain.model.AppResult
import com.mohaberabi.pokemoncompose.core.domain.model.DataError
import com.mohaberabi.pokemoncompose.core.domain.model.DispatchersProvider
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo
import com.mohaberabi.pokemoncompose.core.domain.source.PokemonLocalDataSource
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRoomLocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonInfoDao: PokemonInfoDao,

    private val dispatchers: DispatchersProvider
) : PokemonLocalDataSource {
    override suspend fun getPokemonInfo(
        name: String,
    ): AppResult<PokemonInfo?> {
        return withContext(dispatchers.io) {
            try {
                val info = pokemonInfoDao.getPokemonInfo(name)?.toPokemonInfo()
                AppResult.Done(info)
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                AppResult.Error(DataError.LocalError.UNKNOWN)
            }
        }
    }

    override suspend fun getPokemons(page: Int): AppResult<List<Pokemon>> {
        return withContext(dispatchers.io) {
            try {
                val pokes = pokemonDao.getPokemonList(page).map { it.toPokemon() }
                AppResult.Done(pokes)
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                AppResult.Error(DataError.LocalError.UNKNOWN)
            }
        }
    }

    override suspend fun addPokemonInfo(info: PokemonInfo): AppResult<Unit> {
        return withContext(dispatchers.io) {
            try {
                val pokes = pokemonInfoDao.insertPokemonInfo(info.toPokemonInfoEntity())
                AppResult.Done(pokes)
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                AppResult.Error(DataError.LocalError.UNKNOWN)
            }
        }
    }

    override suspend fun addPokemons(pokemons: List<Pokemon>): AppResult<Unit> {
        return withContext(dispatchers.io) {
            try {
                val pokes = pokemonDao.insertPokemon(pokemons.map { it.toPokemonEntity() })
                AppResult.Done(pokes)
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                AppResult.Error(DataError.LocalError.UNKNOWN)
            }
        }
    }

}