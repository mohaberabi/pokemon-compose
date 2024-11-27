package com.mohaberabi.pokemoncompose.core.domain.usecase

import com.mohaberabi.pokemoncompose.core.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {


    suspend operator fun invoke(name: String) = pokemonRepository.getPokemonInfo(name)
}