package com.example.retrofitflow.service

import com.example.retrofitflow.model.RandomActivityModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/activity")
    suspend fun getActivity() : Response<RandomActivityModel>
}