package com.lisandrolopez.krikeychallenge.repository.model

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("breeds")
    val breeds: List<Breed>? = null
)