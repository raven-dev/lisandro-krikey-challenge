package com.lisandrolopez.krikeychallenge.repository.network

import com.lisandrolopez.krikeychallenge.BuildConfig
import com.lisandrolopez.krikeychallenge.repository.network.interceptor.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {
    fun create(): Api {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        val clientConfig = OkHttpClient.Builder()
        val client = clientConfig
            .addInterceptor(interceptor)
            .addNetworkInterceptor(NetworkInterceptor())
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(Api::class.java)
    }

    companion object {
        private var INSTANCE: Api? = null
        fun getInstance(): Api {
            if (INSTANCE == null) {
                INSTANCE = Network().create()
            }
            return INSTANCE!!
        }
    }
}
