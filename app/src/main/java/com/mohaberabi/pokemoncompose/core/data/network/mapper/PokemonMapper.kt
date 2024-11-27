package com.mohaberabi.pokemoncompose.core.data.network.mapper

import com.mohaberabi.pokemoncompose.core.data.network.dto.PokemonDto
import com.mohaberabi.pokemoncompose.core.data.network.dto.PokemonInfoDto
import com.mohaberabi.pokemoncompose.core.data.network.dto.PokemonResponseDto
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonResponseModel


fun PokemonDto.toPokemon() =
    Pokemon(
        page = page,
        name = name,
        url = url,
        imageUrl = getImageUrl(),
        formattedName = formattedName()
    )


fun PokemonInfoDto.toPokemonInfo() = PokemonInfo(
    id = id,
    name = name,
    speed = speed,

    defense = defense,
    exp = exp,
    experience = experience ?: 0,
    height = height,
    weight = weight,
    attack = attack,
    hp = hp
)