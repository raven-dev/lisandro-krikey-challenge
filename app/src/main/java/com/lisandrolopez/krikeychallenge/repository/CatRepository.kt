package com.lisandrolopez.krikeychallenge.repository

import com.lisandrolopez.krikeychallenge.repository.model.Cat
import com.lisandrolopez.krikeychallenge.repository.network.Api
import com.lisandrolopez.krikeychallenge.repository.network.util.Resource
import com.lisandrolopez.krikeychallenge.repository.network.util.apiCallHandler
import kotlinx.coroutines.Dispatchers

class CatRepository (private val api: Api) {

    suspend fun getKitties(): Resource<List<Cat>?> {
        return apiCallHandler(Dispatchers.IO) {
            api.getKitties()
        }
    }
}
