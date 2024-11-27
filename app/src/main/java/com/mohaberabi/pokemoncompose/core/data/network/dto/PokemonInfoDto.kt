package com.mohaberabi.pokemoncompose.core.data.network.dto

import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_ATTACK
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_DEFENSE
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_EXP
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_HP
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_SPEED
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class PokemonInfoDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int? = null,
    val types: List<TypeResponseDto>,
    val hp: Int = Random.nextInt(MAX_HP),
    val attack: Int = Random.nextInt(MAX_ATTACK),
    val defense: Int = Random.nextInt(MAX_DEFENSE),
    val speed: Int = Random.nextInt(MAX_SPEED),
    val exp: Int = Random.nextInt(MAX_EXP),
) {

    @Serializable
    data class TypeResponseDto(
        val slot: Int,
        val type: TypeDto
    )

    @Serializable

    data class TypeDto(
        val name: String
    )


}