package com.mohaberabi.pokemoncompose.features.info.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo
import com.mohaberabi.pokemoncompose.core.domain.model.onDone
import com.mohaberabi.pokemoncompose.core.domain.model.onError
import com.mohaberabi.pokemoncompose.core.domain.usecase.GetPokemonInfoUseCase
import com.mohaberabi.pokemoncompose.core.presentation.navigation.InfoRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPokemonInfo: GetPokemonInfoUseCase
) : ViewModel() {

    private val route = savedStateHandle.toRoute<InfoRoute>()

    private val _state = MutableStateFlow(PokemonInfoState(pokemon = route.pokemon))
    val state = _state.onStart { getPokemonInfo() }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L),
        PokemonInfoState(pokemon = route.pokemon)
    )

    private fun getPokemonInfo() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            getPokemonInfo(route.pokemon.name)
                .onError { _state.update { it.copy(loading = false) } }
                .onDone { info -> _state.update { it.copy(loading = false, info = info) } }
        }
    }

}