package com.mohaberabi.pokemoncompose.features.info.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_ATTACK
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_DEFENSE
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_EXP
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_HP
import com.mohaberabi.pokemoncompose.core.domain.model.PokemonInfo.Companion.MAX_SPEED
import com.mohaberabi.pokemoncompose.core.presentation.compose.NetworkImage
import com.mohaberabi.pokemoncompose.core.presentation.compose.PokemonProgress
import com.mohaberabi.pokemoncompose.core.presentation.util.dominantColor
import com.mohaberabi.pokemoncompose.features.info.viewmodel.PokemonInfoState
import com.mohaberabi.pokemoncompose.features.info.viewmodel.PokemonInfoViewModel
import com.mohaberabi.pokemoncompose.ui.theme.PokemonComposeTheme


@Composable
fun PokemonInfoScreenRoot(
    viewmodel: PokemonInfoViewModel = hiltViewModel(),
    onBack: () -> Unit = {},

    ) {

    val state by viewmodel.state.collectAsStateWithLifecycle()

    PokemonInfoScreen(
        state = state,
        onBack = onBack
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonInfoScreen(
    modifier: Modifier = Modifier,
    state: PokemonInfoState,
    onBack: () -> Unit = {},
) {


    var pokemonColor by remember {
        mutableStateOf(Color.Transparent)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = pokemonColor
                ),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back", tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
            )
        }
    ) { padding ->


        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(pokemonColor),
                contentAlignment = Alignment.Center
            ) {
                NetworkImage(
                    url = state.pokemon.imageUrl,
                    requestBuilder = {
                        dominantColor {
                            pokemonColor = Color(it)
                        }
                    },
                )
            }

            Box(
                contentAlignment = Alignment.Center,
            ) {
                when {
                    state.loading -> CircularProgressIndicator()
                    else -> {
                        state.info?.let { info ->
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = info.name,
                                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                PokemonProgress(
                                    currentProgress = info.hp,
                                    maxProgress = MAX_HP,
                                    label = "HP",
                                    progressColor = Color.Magenta
                                )
                                PokemonProgress(
                                    currentProgress = info.attack,
                                    maxProgress = MAX_ATTACK,
                                    label = "ATK",
                                )
                                PokemonProgress(
                                    currentProgress = info.defense,
                                    maxProgress = MAX_DEFENSE,
                                    label = "DEF",
                                    progressColor = MaterialTheme.colorScheme.primary
                                )
                                PokemonProgress(
                                    currentProgress = info.speed,
                                    maxProgress = MAX_SPEED,
                                    label = "SPD",
                                    progressColor = Color.Blue
                                )
                                PokemonProgress(
                                    currentProgress = info.experience,
                                    maxProgress = MAX_EXP,
                                    label = "EXP",
                                    progressColor = Color.Green
                                )
                            }
                        } ?: Text(text = "Something Went Wrong")
                    }
                }
            }


        }


    }

}


@Preview(
    showBackground = true
)
@Composable
private fun PreviewPokemonInfo() {


    PokemonComposeTheme {


        PokemonInfoScreen(state = PokemonInfoState(pokemon = Pokemon(0, "MOHAB ERABI", "", "", "")))
    }
}