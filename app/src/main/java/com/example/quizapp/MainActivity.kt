package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // find views
        val startButton : Button = findViewById(R.id.btn_start)
        val etName: EditText = findViewById(R.id.et_name)

        /**
         *  Start button's handler.
         *  If etName is empty, show Toast to let users know they must enter their name.
         *  Otherwise, start and pass the user's name to QuizQuestionActivity.
         */
        startButton.setOnClickListener {
            if (etName.text.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }


}