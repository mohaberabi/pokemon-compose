package com.mohaberabi.pokemoncompose.core.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Pokemon(
    val page: Int = 0,
    val name: String,
    val url: String,
    val imageUrl: String,
    val formattedName: String,
)