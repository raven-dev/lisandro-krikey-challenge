package com.lisandrolopez.krikeychallenge.repository.network.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

//TODO: Add more info about errors. Also add the parser for error api.
suspend fun <T> apiCallHandler(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): Resource<T> {
    return withContext(dispatcher) {
        try {
            Resource.success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Resource.error<T>("network error")
                else -> {
                    Resource.error<T>("something went wrong")
                }
            }
        }
    }
}