package com.mohaberabi.pokemoncompose.features.info.viewmodel

import androidx.compose.runtime.Stable
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo
import com.mohaberabi.pokemoncompose.core.presentation.model.UiText

@Stable
data class PokemonInfoState(
    val loading: Boolean = false,
    val pokemon: Pokemon,
    val info: PokemonInfo? = null,
    val error: UiText = UiText.Empty
)
