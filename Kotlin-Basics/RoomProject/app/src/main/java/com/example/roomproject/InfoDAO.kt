package com.example.roomproject

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface InfoDAO {

    @Insert
    suspend fun insert(info: Information)

    @Query("SELECT * FROM person")
    fun getAll(): LiveData<List<Information>>

}