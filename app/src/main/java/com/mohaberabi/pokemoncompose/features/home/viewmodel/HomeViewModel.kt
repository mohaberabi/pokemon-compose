package com.mohaberabi.pokemoncompose.features.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pokemoncompose.core.domain.model.onDone
import com.mohaberabi.pokemoncompose.core.domain.model.onError
import com.mohaberabi.pokemoncompose.core.domain.usecase.GetPokemonsUseCase
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
class HomeViewModel @Inject constructor(
    private val getPokemon: GetPokemonsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


    companion object {
        const val INDEX_KEY = "index"
    }

    private val _state = MutableStateFlow(HomeState())
    val state = _state.onStart { getPokemons() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            HomeState()
        )

    fun loadMore() {
        val page = savedStateHandle[INDEX_KEY] ?: 0
        savedStateHandle[INDEX_KEY] = page + 1
        getPokemons(page + 1, false)
    }

    private fun getPokemons(
        page: Int = 0,
        shouldLoad: Boolean = true
    ) {
        viewModelScope.launch {
            _state.update { it.copy(loading = shouldLoad) }
            getPokemon(page)
                .onDone { pokes ->
                    _state.update { it.copy(loading = false, pokemon = it.pokemon + pokes) }
                }.onError {
                    _state.update { it.copy(loading = false) }
                }
        }
    }


}