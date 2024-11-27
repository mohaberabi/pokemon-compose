package com.mohaberabi.pokemoncompose.core.presentation.model

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource


sealed interface UiText {


    data class Dynamic(
        val value: String,
    ) : UiText

    class StringResource(
        @StringRes val res: Int,
        vararg val formatArgs: Any?,
    ) : UiText

    data object Empty : UiText

    val isEmpty: Boolean
        get() = this is Empty

    val isNotEmpty: Boolean get() = !isEmpty

    @Composable
    fun fold(): String {
        val context = LocalContext.current
        return when (this) {
            is Dynamic -> this.value
            Empty -> ""
            is StringResource -> stringResource(id = this.res, this.formatArgs)
        }
    }

    fun fold(context: Context): String {
        return when (this) {
            is Dynamic -> this.value
            Empty -> ""
            is StringResource -> context.getString(this.res, this.formatArgs)
        }
    }
}