package com.mohaberabi.pokemoncompose.core.presentation.util

import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import coil.request.ImageRequest


inline fun ImageRequest.Builder.dominantColor(
    crossinline onReceive: (Int) -> Unit,
): ImageRequest.Builder {
    return listener { _, result ->
        val bitmap = (result.drawable as? BitmapDrawable)?.bitmap
        bitmap?.let {
            Palette.Builder(it).generate { pallete ->
                pallete?.dominantSwatch?.rgb?.let { rgb ->
                    onReceive(rgb)
                }
            }
        }
    }
}