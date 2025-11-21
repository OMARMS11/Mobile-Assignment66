package com.example.team66assignment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quiz::class], version = 1)
abstract class QuizDataBase : RoomDatabase() {
    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDataBase? = null

        private fun getInstance(context: Context): QuizDataBase {

            synchronized(QuizDataBase::class){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDataBase::class.java,
                    "quiz_database"
                ).build()

            }
            return INSTANCE!!
        }
    }
}