package com.example.roomproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Information::class], version = 1)
abstract class InfoDatabase : RoomDatabase(){

    abstract fun infoDao(): InfoDAO

    companion object {
        @Volatile
        private var instance: InfoDatabase? = null

        fun getDatabase(context: Context): InfoDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    InfoDatabase::class.java,
                    "person_database"
                ).build()
                //instance = database
                database
            }
        }
    }
}