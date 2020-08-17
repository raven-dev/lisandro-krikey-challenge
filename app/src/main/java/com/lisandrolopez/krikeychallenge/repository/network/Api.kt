package com.lisandrolopez.krikeychallenge.repository.network

import com.lisandrolopez.krikeychallenge.repository.model.Cat
import retrofit2.http.GET

interface Api {
    @GET("images/search?limit=5")
    suspend fun getKitties(): List<Cat>
}
