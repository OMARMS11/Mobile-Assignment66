package com.example.team66assignment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizDao {
    @Insert
    suspend fun insertQuiz(quiz: Quiz)

    @Query("SELECT * FROM quiz_table WHERE category = :category")
    suspend fun getQuizByCategory(category: String): List<Quiz>



}