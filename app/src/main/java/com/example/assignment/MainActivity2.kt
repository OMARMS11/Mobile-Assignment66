package com.example.team66assignment

import android.os.Bundle
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.team66assignment.Quiz
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db =  QuizDataBase.getInstance(this)
        var currentQuestion: Quiz? = null
        val spinner = findViewById<Spinner>(R.id.spinner)
        val answerTextView = findViewById<TextView>(R.id.textView2)
        CoroutineScope(Dispatchers.IO).launch {
            val names = db.quizDao().getCategories()   // put here DAO name

            withContext(Dispatchers.Main) {

                val adapter :  ArrayAdapter<String> = ArrayAdapter(
                    this@MainActivity2,
                    android.R.layout.simple_spinner_item,
                    names
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.adapter = adapter


            }
            val button = findViewById<Button>(R.id.button2_show)
            val questionTextView = findViewById<TextView>(R.id.textView)
            button.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val selectedCategory = spinner.selectedItem.toString()

                    val randomQuestion = db.quizDao().getQuizByCategory(selectedCategory)

                    withContext(Dispatchers.Main) {
                        if (randomQuestion == null) {
                            Toast.makeText(
                                this@MainActivity2,
                                "No questions in this category",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else {

                            currentQuestion = randomQuestion

                            questionTextView.text =
                                randomQuestion.question // change the attribute here

                        }
                    }

                }
            }
            val button2 = findViewById<Button>(R.id.button2_show2)
            button2.setOnClickListener {
                if (currentQuestion == null) {
                    Toast.makeText(this@MainActivity2, "Click 'Show Question' first", Toast.LENGTH_SHORT).show()
                } else {
                    answerTextView.text = currentQuestion.answer // CHANGE THE ATTRIBUTE HERE
                }
            }
            }

        }
    }


