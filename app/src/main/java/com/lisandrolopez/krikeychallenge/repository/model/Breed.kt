package com.lisandrolopez.krikeychallenge.repository.model

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("vetstreet_url")
    val vetStreetUrl: String? = null,
    @SerializedName("temperament")
    val temperament: String? = null,
    @SerializedName("origin")
    val origin: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String? = null,
    @SerializedName("weight")
    val weight: Weight? = null
)