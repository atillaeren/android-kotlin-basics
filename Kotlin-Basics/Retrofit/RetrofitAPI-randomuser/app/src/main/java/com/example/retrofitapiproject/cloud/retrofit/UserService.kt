package com.example.retrofitapiproject.cloud.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api")
    suspend fun getUsers(
        @Query("results") results: Int = 1
    ): Response<ApiResponse>
}