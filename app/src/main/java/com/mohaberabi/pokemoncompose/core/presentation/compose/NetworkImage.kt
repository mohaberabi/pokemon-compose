package com.mohaberabi.pokemoncompose.core.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mohaberabi.pokemoncompose.R
import com.mohaberabi.pokemoncompose.core.presentation.util.dominantColor


@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    requestBuilder: (ImageRequest.Builder.() -> ImageRequest.Builder)? = null,
) {
    val isPreview = LocalInspectionMode.current

    if (isPreview) {
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.pl),
            contentDescription = contentDescription
        )
    } else {
        val context = LocalContext.current

        AsyncImage(

            modifier = modifier,
            error = painterResource(id = R.drawable.pl),
            placeholder = painterResource(id = R.drawable.pl),
            model = ImageRequest.Builder(context)
                .data(url)
                .crossfade(true)
                .allowHardware(false).apply {
                    requestBuilder?.let {
                        it()
                    }
                }.build(),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )
    }

}