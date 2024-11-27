package com.mohaberabi.pokemoncompose.core.data.database.mapper

import com.mohaberabi.pokemoncompose.core.data.database.entity.PokemonEntity
import com.mohaberabi.pokemoncompose.core.data.database.entity.PokemonInfoEntity
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo

fun Pokemon.toPokemonEntity() =
    PokemonEntity(
        page = page,
        name = name,
        url = url,
    )


fun PokemonInfo.toPokemonInfoEntity() = PokemonInfoEntity(
    id = id,
    name = name,
    speed = speed,
    defense = defense,
    exp = exp,
    experience = experience,
    height = height,
    weight = weight,
    attack = attack,
    hp = hp,
    imageUlr = imageUrl
)

fun PokemonEntity.toPokemon() =
    Pokemon(
        page = page,
        name = name,
        url = url,
        imageUrl = getImageUrl(),
        formattedName = formattedName()
    )


fun PokemonInfoEntity.toPokemonInfo() = PokemonInfo(
    id = id,
    name = name,
    speed = speed,

    defense = defense,
    exp = exp,
    experience = experience,
    height = height,
    weight = weight,
    attack = attack,
    hp = hp,
    imageUrl = imageUlr
)