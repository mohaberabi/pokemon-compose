package com.mohaberabi.pokemoncompose.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohaberabi.pokemoncompose.core.data.serializableType
import com.mohaberabi.pokemoncompose.core.domain.model.Pokemon
import com.mohaberabi.pokemoncompose.features.home.screens.HomeScreenRoot
import com.mohaberabi.pokemoncompose.features.info.screen.PokemonInfoScreenRoot
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf


@Serializable
data object HomeRoute


@Serializable
data class InfoRoute(val pokemonSerial: String) {

    val pokemon get() = Json.decodeFromString<Pokemon>(pokemonSerial)
}

@Composable
fun AppNavHost(


    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController, startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            HomeScreenRoot(
                onPokemonClick = { navController.goToPokemonInfo(it) },
            )
        }
        composable<InfoRoute> {
            PokemonInfoScreenRoot(onBack = { navController.popBackStack() })
        }
    }
}

fun NavController.goToPokemonInfo(pokemon: Pokemon) = navigate(InfoRoute(Json.encodeToString(pokemon)))