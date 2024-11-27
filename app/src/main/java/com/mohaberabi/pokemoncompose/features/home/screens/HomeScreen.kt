package com.mohaberabi.pokemoncompose.features.home.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.presentation.compose.PokemonCard
import com.mohaberabi.pokemoncompose.features.home.viewmodel.HomeState
import com.mohaberabi.pokemoncompose.features.home.viewmodel.HomeViewModel
import com.mohaberabi.pokemoncompose.ui.theme.PokemonComposeTheme


@Composable
fun HomeScreenRoot(
    viewmodel: HomeViewModel = hiltViewModel(),
    onPokemonClick : (Pokemon) -> Unit,
) {


    val state by viewmodel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onLoadMore = viewmodel::loadMore,
        onPoekmonClick = onPokemonClick
    )
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onLoadMore: () -> Unit = {},
    onPoekmonClick: (Pokemon) -> Unit = {},
) {


    val lazyListState = rememberLazyGridState()
    LaunchedEffect(
        key1 = lazyListState,
    ) {
        snapshotFlow {
            lazyListState.reachedEnd()
        }.collect { reached ->
            if (reached) {
                onLoadMore()
            }
        }
    }
    Scaffold { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {
            when {
                state.loading -> CircularProgressIndicator()
                else -> {
                    LazyVerticalGrid(
                        contentPadding = PaddingValues(4.dp),
                        columns = GridCells.Fixed(2),
                        state = lazyListState
                    ) {
                        items(state.pokemon) { poke ->
                            PokemonCard(
                                imageUrl = poke.imageUrl,
                                onClick = {onPoekmonClick(poke)},
                                name = poke.name,
                            )
                        }
                    }

                }
            }
        }


    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewHomeScreen() {
    PokemonComposeTheme {

        HomeScreen(state = HomeState())
    }
}

fun LazyGridState.reachedEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
