package com.mohaberabi.pokemoncompose.core.data.source.remote

import com.mohaberabi.pokemoncompose.core.data.network.NetworkConst
import com.mohaberabi.pokemoncompose.core.data.network.dto.PokemonInfoDto
import com.mohaberabi.pokemoncompose.core.data.network.dto.PokemonResponseDto
import com.mohaberabi.pokemoncompose.core.data.network.mapper.toPokemon
import com.mohaberabi.pokemoncompose.core.data.network.mapper.toPokemonInfo
import com.mohaberabi.pokemoncompose.core.data.network.util.safeRemoteCall
import com.mohaberabi.pokemoncompose.core.domain.model.AppResult
import com.mohaberabi.pokemoncompose.core.domain.model.DispatchersProvider
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo
import com.mohaberabi.pokemoncompose.core.domain.model.map
import com.mohaberabi.pokemoncompose.core.domain.source.PokemonRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext

class KtorPokemonRemoteDataSource(
    private val dispatchers: DispatchersProvider,
    private val client: HttpClient,
) : PokemonRemoteDataSource {
    override suspend fun getPokemonInfo(name: String): AppResult<PokemonInfo?> {

        return withContext(dispatchers.io) {
            safeRemoteCall<PokemonInfoDto> {
                client.get(
                    urlString = "${NetworkConst.BASE_URL}pokemon/${name}",
                )
            }.map { it.toPokemonInfo() }
        }

    }

    override suspend fun getPokemons(page: Int): AppResult<List<Pokemon>> {
        return withContext(dispatchers.io) {
            safeRemoteCall<PokemonResponseDto> {
                client.get(
                    urlString = "${NetworkConst.BASE_URL}pokemon",
                ) {
                    parameter("page", page)
                    parameter("limit", 20)
                }
            }.map { it.results.map { pok -> pok.toPokemon() } }
        }
    }


}