package com.mohaberabi.pokemoncompose.core.domain.model


sealed interface AppResult<out T> {
    data class Done<T>(val data: T) : AppResult<T>
    data class Error(val error: AppError) : AppResult<Nothing>
}

inline fun <reified T> AppResult<T>.onError(
    block: (AppError) -> Unit
): AppResult<T> {

    if (this is AppResult.Error) {
        block(this.error)
    }
    return this
}

inline fun <reified T> AppResult<T>.onDone(
    block: (T) -> Unit
): AppResult<T> {

    if (this is AppResult.Done) {
        block(this.data)
    }
    return this
}

inline fun <T, R> AppResult<T>.map(map: (T) -> R): AppResult<R> {
    return when (this) {
        is AppResult.Error -> AppResult.Error(error)
        is AppResult.Done -> AppResult.Done(map(data))
    }
}