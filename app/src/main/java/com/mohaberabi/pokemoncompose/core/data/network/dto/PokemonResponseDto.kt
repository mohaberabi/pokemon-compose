package com.mohaberabi.pokemoncompose.core.data.network.dto

import kotlinx.serialization.Serializable


@Serializable
data class PokemonResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonDto>
)