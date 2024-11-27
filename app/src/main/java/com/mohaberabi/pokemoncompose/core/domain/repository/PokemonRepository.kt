package com.mohaberabi.pokemoncompose.core.domain.repository

import com.mohaberabi.pokemoncompose.core.domain.model.AppResult
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo

interface PokemonRepository {

    suspend fun getPokemonInfo(
        name: String,
    ): AppResult<PokemonInfo?>


    suspend fun getPokemons(
        page: Int = 0,
    ): AppResult<List<Pokemon>>
}