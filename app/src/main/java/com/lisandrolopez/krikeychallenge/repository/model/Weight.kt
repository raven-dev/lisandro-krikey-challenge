package com.lisandrolopez.krikeychallenge.repository.model

import com.google.gson.annotations.SerializedName

data class Weight(
    @SerializedName("imperial")
    val imperial: String? = null,
    @SerializedName("metric")
    val metric: String? = null
)