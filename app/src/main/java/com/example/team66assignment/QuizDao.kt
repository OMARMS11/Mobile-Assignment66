package com.example.team66assignment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizDao {
    @Insert
    suspend fun insertQuiz(quiz: Quiz)

    @Query("SELECT * FROM quiz_table WHERE category = :category ORDER BY RANDOM() LIMIT 1")
    suspend fun getQuizByCategory(category: String): Quiz?

    @Query("select category from quiz_table")
    suspend fun getCategories(): List<String>


}