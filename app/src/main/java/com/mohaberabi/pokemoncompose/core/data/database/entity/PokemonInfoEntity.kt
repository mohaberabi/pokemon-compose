package com.mohaberabi.pokemoncompose.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonInfoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val exp: Int,
    val imageUlr: String,
)
