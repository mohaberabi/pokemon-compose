package com.mohaberabi.pokemoncompose.core.data.network.util

import com.mohaberabi.pokemoncompose.core.domain.model.AppResult
import com.mohaberabi.pokemoncompose.core.domain.model.DataError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import java.net.SocketTimeoutException
import kotlin.coroutines.coroutineContext


suspend inline fun <reified T> safeRemoteCall(
    block: () -> HttpResponse
): AppResult<T> {


    val response = try {
        block()
    } catch (e: SocketTimeoutException) {
        return AppResult.Error(DataError.RemoteError.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return AppResult.Error(DataError.RemoteError.NO_INTERNET)

    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return AppResult.Error(DataError.RemoteError.UNKNOWN)
    }
    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): AppResult<T> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                AppResult.Done(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                AppResult.Error(DataError.RemoteError.SERIALIZATION)
            }
        }

        408 -> AppResult.Error(DataError.RemoteError.REQUEST_TIMEOUT)
        429 -> AppResult.Error(DataError.RemoteError.TOO_MANY_REQUESTS)
        in 500..599 -> AppResult.Error(DataError.RemoteError.SERVER)
        else -> AppResult.Error(DataError.RemoteError.UNKNOWN)
    }
}