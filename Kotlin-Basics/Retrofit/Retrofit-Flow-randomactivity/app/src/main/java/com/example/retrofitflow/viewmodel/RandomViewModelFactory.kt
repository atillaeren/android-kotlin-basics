package com.example.retrofitflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.retrofitflow.model.Repository

class RandomViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(RandomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RandomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}