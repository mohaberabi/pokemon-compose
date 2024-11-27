package com.mohaberabi.pokemoncompose.core.domain.model

sealed interface DataError : AppError {


    enum class RemoteError : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }


    enum class LocalError : DataError {
        DISK_FULL,
        UNKNOWN
    }
}