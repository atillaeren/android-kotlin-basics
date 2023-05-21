package com.example.roomproject

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Single

class InfoRepository(private val infoDAO: InfoDAO) {

    val allUsers: LiveData<List<Information>> = infoDAO.getAll()

    suspend fun insert(info: Information) {
        infoDAO.insert(info)
    }
}