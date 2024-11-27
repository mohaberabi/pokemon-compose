package com.mohaberabi.pokemoncompose.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    var page: Int = 0,
    @PrimaryKey val name: String,
    val url: String,
) {
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                "pokemon/other/official-artwork/$index.png"
    }

    fun formattedName(): String = name.replaceFirstChar { it.uppercase() }
}
