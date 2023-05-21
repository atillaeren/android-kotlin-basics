package com.example.retrofitapiproject.model

import com.google.gson.annotations.SerializedName

data class RandomUser(
    val results: List<User>,
    ) {
    data class User(
        @SerializedName("gender")
        val Gender: String,
        val name: Name,
        val location: Location,
        val nat: String
    ) {
        data class Name(
            val title: String,
            val first: String,
            val last: String
        )
        data class Location(
            val street: Street,
            val city: String,
            val state: String,
            val country: String,
            val timezone: Timezone
        ) {
            data class Street(
                val number: Int,
                val name: String
            )
            data class Timezone(
                val offset: String,
                val description: String
            )
        }
    }
}
