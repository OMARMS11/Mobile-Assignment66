package com.example.team66assignment

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.team66assignment.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val question = findViewById<EditText>(R.id.editTextQuestion)
        val answer = findViewById<EditText>(R.id.editTextAnswer)
        val category = findViewById<EditText>(R.id.editTextCategory)

        val addButton = findViewById<Button>(R.id.buttonAddQuiz)
        val goToQuizButton = findViewById<Button>(R.id.buttonGoToQuiz)
        val db = QuizDataBase.getInstance(this)


        addButton.setOnClickListener {
            val questionText = question.text.toString()
            val answerText = answer.text.toString()
            val categoryText = category.text.toString()

            // Add the quiz to the database
            lifecycleScope.launch(Dispatchers.IO) {
                val quiz =
                    Quiz(question = questionText, answer = answerText, category = categoryText)
                db.quizDao().insertQuiz(quiz)
                withContext(Dispatchers.Main) {
                    question.text.clear()
                    answer.text.clear()
                    category.text.clear()
                    Toast.makeText(this@MainActivity, "Quiz added", Toast.LENGTH_SHORT).show()
                }
            }


    }

    }
}