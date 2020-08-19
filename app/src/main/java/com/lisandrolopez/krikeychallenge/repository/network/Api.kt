package com.lisandrolopez.krikeychallenge.repository.network

import com.lisandrolopez.krikeychallenge.repository.model.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("images/search?limit=20")
    suspend fun getKitties(@Query("breed_ids") been: String): List<Cat>
}
