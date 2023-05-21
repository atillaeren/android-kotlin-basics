package com.example.retrofitflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitflow.model.RandomActivityModel
import com.example.retrofitflow.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response


class RandomViewModel(private val repository: Repository) : ViewModel() {

    val activity: Flow<Response<RandomActivityModel>> = flow {
        val apiResponse = repository.getActivity()
        emit(apiResponse)
    }
}