package com.mohaberabi.pokemoncompose.core.domain.model

data class PokemonResponseModel(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)