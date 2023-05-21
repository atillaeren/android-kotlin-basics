package com.example.retrofitapiproject.model

import com.example.retrofitapiproject.cloud.retrofit.ApiResponse
import com.example.retrofitapiproject.cloud.retrofit.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    private val apiService: UserService by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    suspend fun getUsers(): ApiResponse {
        return apiService.getUsers().body()!!
    }
}