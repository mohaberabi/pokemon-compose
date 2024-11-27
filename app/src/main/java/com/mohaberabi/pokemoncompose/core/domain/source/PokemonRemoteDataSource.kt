package com.mohaberabi.pokemoncompose.core.domain.source

import com.mohaberabi.pokemoncompose.core.domain.model.AppResult
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo

interface PokemonRemoteDataSource {
    suspend fun getPokemonInfo(
        name: String,
    ): AppResult<PokemonInfo?>

    suspend fun getPokemons(
        page: Int = 0,
    ): AppResult<List<Pokemon>>
}