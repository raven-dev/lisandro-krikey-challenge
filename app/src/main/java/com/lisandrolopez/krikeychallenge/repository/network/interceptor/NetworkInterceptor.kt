package com.lisandrolopez.krikeychallenge.repository.network.interceptor

import com.lisandrolopez.krikeychallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestBuilder: Request.Builder
        val request: Request
        requestBuilder = originalRequest.newBuilder()
        requestBuilder.apply {
            header("x-api-key", BuildConfig.API_KEY)
            method(originalRequest.method, originalRequest.body)
        }

        request = requestBuilder.build()

        return chain.proceed(request)
    }

}
