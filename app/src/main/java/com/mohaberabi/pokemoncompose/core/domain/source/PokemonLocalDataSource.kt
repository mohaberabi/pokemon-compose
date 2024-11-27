package com.mohaberabi.pokemoncompose.core.domain.source

import com.mohaberabi.pokemoncompose.core.domain.model.AppResult
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo


interface PokemonLocalDataSource {
    suspend fun getPokemonInfo(
        name: String,
    ): AppResult<PokemonInfo?>

    suspend fun getPokemons(
        page: Int = 0,
    ): AppResult<List<Pokemon>>

    suspend fun addPokemonInfo(info: PokemonInfo): AppResult<Unit>

    suspend fun addPokemons(pokemons: List<Pokemon>): AppResult<Unit>
}