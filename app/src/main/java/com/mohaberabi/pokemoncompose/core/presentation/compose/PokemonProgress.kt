package com.mohaberabi.pokemoncompose.core.presentation.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.pokemoncompose.ui.theme.PokemonComposeTheme
import kotlinx.coroutines.delay


@Composable
fun PokemonProgress(
    modifier: Modifier = Modifier,
    currentProgress: Int,
    maxProgress: Int,
    label: String,
    progressColor: Color = MaterialTheme.colorScheme.error
) {


    var progress by remember {

        mutableFloatStateOf(0f)
    }
    LaunchedEffect(key1 = Unit) {
        delay(100L)
        progress = if (maxProgress != 0) currentProgress.toFloat() / maxProgress.toFloat() else 0f
    }
    val targetProgress by animateFloatAsState(
        targetValue = progress,
        label = "FloatAnimation",
    )


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = label,
        )
        Spacer(
            modifier = Modifier.width(8.dp),
        )

        Box(
            contentAlignment = Alignment.Center,
        ) {
            LinearProgressIndicator(
                modifier = Modifier.height(16.dp),
                strokeCap = StrokeCap.Round,
                progress = { targetProgress },
                trackColor = Color.LightGray,
                color = progressColor,
            )
            Text(
                text = "${currentProgress}/${maxProgress}",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimary)
            )
        }

    }

}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewPokemonProgres() {

    PokemonComposeTheme {

        PokemonProgress(
            modifier = Modifier.fillMaxSize(),
            currentProgress = 100,
            maxProgress = 200,
            label = "ATK"
        )
    }
}