package com.example.retrofitflow.model

import com.google.gson.annotations.SerializedName

data class RandomActivityModel(
    @SerializedName("activity")
    val activity: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("participants")
    val participants: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("accessibility")
    val accessibility: Double
)