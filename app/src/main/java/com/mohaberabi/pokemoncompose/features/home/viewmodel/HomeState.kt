package com.mohaberabi.pokemoncompose.features.home.viewmodel

import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.presentation.model.UiText

data class HomeState(
    val loading: Boolean = false,
    val pokemon: List<Pokemon> = listOf(),
    val error: UiText = UiText.Empty
)
