package com.example.roomproject

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class InfoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: InfoRepository


    val allUsers: LiveData<List<Information>>

    init {
        val infoDao = InfoDatabase.getDatabase(application).infoDao()
        repository = InfoRepository(infoDao)
        allUsers = repository.allUsers
    }

    fun insert(info: Information) = viewModelScope.launch {
        repository.insert(info)
    }
}