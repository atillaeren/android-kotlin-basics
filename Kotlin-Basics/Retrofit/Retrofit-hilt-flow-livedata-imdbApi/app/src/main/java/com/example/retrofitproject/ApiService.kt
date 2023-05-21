package com.example.retrofitproject

import retrofit2.http.GET

interface ApiService {
    @GET("API/Top250Movies/your_api_key_here")
    suspend fun getMostPopularMovies() : MovieResponse
}