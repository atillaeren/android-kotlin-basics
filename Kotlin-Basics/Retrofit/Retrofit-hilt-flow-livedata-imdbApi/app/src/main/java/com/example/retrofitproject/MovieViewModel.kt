package com.example.retrofitproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var _movieResponse = MutableLiveData<NetworkResult<List<Movie>>>()

    val movieResponse: LiveData<NetworkResult<List<Movie>>> = _movieResponse

    init {
        fetchAllMovies()
    }

    private fun fetchAllMovies() {
        viewModelScope.launch {
            repository.getPopularMovies().collect {
                _movieResponse.postValue(it)
            }
        }
    }
}