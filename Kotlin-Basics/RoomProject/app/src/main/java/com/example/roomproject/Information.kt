package com.example.roomproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Information(
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0,
    val personName: String?,
    val personSurname: String?,
    val personAge: String?,
    val personEmail: String?
)

