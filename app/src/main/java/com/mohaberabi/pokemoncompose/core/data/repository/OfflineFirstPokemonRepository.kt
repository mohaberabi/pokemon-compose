package com.mohaberabi.pokemoncompose.core.data.repository

import com.mohaberabi.pokemoncompose.core.data.source.remote.KtorPokemonRemoteDataSource
import com.mohaberabi.pokemoncompose.core.domain.model.AppResult
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo
import com.mohaberabi.pokemoncompose.core.domain.model.map
import com.mohaberabi.pokemoncompose.core.domain.model.onDone
import com.mohaberabi.pokemoncompose.core.domain.model.onError
import com.mohaberabi.pokemoncompose.core.domain.repository.PokemonRepository
import com.mohaberabi.pokemoncompose.core.domain.source.PokemonLocalDataSource
import com.mohaberabi.pokemoncompose.core.domain.source.PokemonRemoteDataSource
import javax.inject.Inject

class OfflineFirstPokemonRepository @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource,
) : PokemonRepository {

    override suspend fun getPokemonInfo(
        name: String,
    ): AppResult<PokemonInfo?> {

        return pokemonLocalDataSource.getPokemonInfo(name).map { localPoke ->
            localPoke ?: return pokemonRemoteDataSource.getPokemonInfo(name).map { remote ->
                remote?.let {
                    pokemonLocalDataSource.addPokemonInfo(it)
                }
                remote
            }

        }

    }

    override suspend fun getPokemons(page: Int): AppResult<List<Pokemon>> {
        return when (val local = pokemonLocalDataSource.getPokemons(page)) {
            is AppResult.Done -> {
                if (local.data.isNotEmpty()) {
                    local
                } else {
                    pokemonRemoteDataSource.getPokemons(page).map { pokes ->
                        pokemonLocalDataSource.addPokemons(pokes.map { it.copy(page = page) })
                        pokes
                    }
                }
            }

            is AppResult.Error -> local
        }
    }
}