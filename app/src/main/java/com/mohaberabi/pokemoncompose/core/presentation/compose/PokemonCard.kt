package com.mohaberabi.pokemoncompose.core.presentation.compose

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.mohaberabi.pokemoncompose.R
import com.mohaberabi.pokemoncompose.core.presentation.util.dominantColor
import com.mohaberabi.pokemoncompose.ui.theme.PokemonComposeTheme


@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit,
    imageUrl: String,
) {
    var dominantColor by remember { mutableStateOf(Color.Transparent) }
    Card(
        modifier = modifier
            .height(200.dp)
            .width(200.dp)
            .padding(8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = dominantColor),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            NetworkImage(
                url = imageUrl,
                requestBuilder = {
                    dominantColor { rgb ->
                        dominantColor = Color(rgb)
                    }
                },
            )

            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
                    .copy(color = MaterialTheme.colorScheme.onPrimary)
            )

        }

    }

}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewPokemonCard() {

    PokemonComposeTheme {
        PokemonCard(
            name = "mohab",
            imageUrl = "",
            onClick = {}
        )
    }
}