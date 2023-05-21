package com.example.retrofitapiproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.retrofitapiproject.model.RandomUser
import com.example.retrofitapiproject.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RandomUserViewModel(private val userRepository: Repository) : ViewModel() {

    val users: Flow<List<RandomUser.User>> = flow {
        val apiResponse = userRepository.getUsers()
        emit(apiResponse.results)
    }.flowOn(Dispatchers.IO)


}