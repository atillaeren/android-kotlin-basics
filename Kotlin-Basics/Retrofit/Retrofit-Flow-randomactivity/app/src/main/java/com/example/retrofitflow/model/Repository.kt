package com.example.retrofitflow.model

import com.example.retrofitflow.service.ApiService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    suspend fun getActivity(): Response<RandomActivityModel> {
        val response = apiService.getActivity()
        if (response.isSuccessful && response.body() != null) {
            return response
        } else {
            throw IllegalStateException("Error getting activity")
        }
    }

    companion object {
        private const val BASE_URL = "https://www.boredapi.com/"
    }
}