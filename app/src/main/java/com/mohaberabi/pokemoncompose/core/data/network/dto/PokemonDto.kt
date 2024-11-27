package com.mohaberabi.pokemoncompose.core.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    var page: Int = 0,
    val name: String,
    val url: String
) {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                "pokemon/other/official-artwork/$index.png"
    }

    fun formattedName(): String = name.replaceFirstChar { it.uppercase() }
}