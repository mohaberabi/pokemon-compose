package com.mohaberabi.pokemoncompose.core.domain.usecase

import com.mohaberabi.pokemoncompose.core.domain.model.AppResult
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(page: Int = 0): AppResult<List<Pokemon>> =
        repository.getPokemons(page = page)
}