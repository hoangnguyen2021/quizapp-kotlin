package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), OnClickListener {
    // store all question data
    private var questionsList: ArrayList<Question>? = null
    // store current question index
    private var currentQuestion: Int = 1
    // store option index selected by the user
    private var selectedOption: Int = 0
    // store the user's name
    private var username: String? = null
    // store number of correct answers
    private var correctAnswers: Int = 0

    // store views
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        // store the user's name passed from MainActivity
        username = intent.getStringExtra(Constants.USER_NAME)

        // find views
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        // set on click listener to the 4 options and the submit button
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        // get question data from Constants
        questionsList = Constants.getQuestions()

        // set content for the first question
        setQuestion()
    }

    /**
     *  Sets content for the next question.
     */
    private fun setQuestion() {
        // clear previous styling for the 4 options
        defaultOptionsView()

        // the current question data
        val question: Question = questionsList?.get(currentQuestion - 1)!!

        // set content for the current question
        ivImage?.setImageResource(question.image)
        progressBar?.progress = currentQuestion
        tvProgress?.text = "$currentQuestion/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        /*
            If the final question is reached, submit button's text
            is set to "FINISH". Otherwise, it is set to "SUBMIT".
         */
        if (currentQuestion == questionsList!!.size) {
            btnSubmit?.text = "FINISH"
        } else {
            btnSubmit?.text = "SUBMIT"
        }
    }

    /**
     *  Applies default styling for the 4 options.
     */
    private fun defaultOptionsView() {
        // create an array list to hold 4 options
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        // set default styling for the 4 options
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    /**
     *  Applies selected styling for the selected option.
     *
     *  @param tv the selected option text view
     */
    private fun selectedOptionsView(tv: TextView) {
        // clear previous styling for the 4 options
        defaultOptionsView()

        // apply selected styling for the selected option
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    /**
     *  Styles correct option and wrong option with
     *  the specified drawable view.
     *
     *  @param answer the answer representing the option text view (1-4)
     *  @param drawableView the drawable view
     *                      (correct_option_border_bg or wrong_option_border_bg)
     */
    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    /**
     *  Handles all click events.
     */
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionsView(it)
                }
                selectedOption = 1
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionsView(it)
                }
                selectedOption = 2
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionsView(it)
                }
                selectedOption = 3
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionsView(it)
                }
                selectedOption = 4
            }
            R.id.btn_submit -> {
                if (selectedOption == 0) {
                    // if the selected option is 0, increment currentQuestion
                    currentQuestion++
                    when {
                        // if the end is not reached
                        currentQuestion <= questionsList!!.size -> {
                            // go to next question
                            setQuestion()
                        }
                        else -> {
                            // otherwise go to ResultActivity
                            val intent = Intent(this, ResultActivity::class.java)
                            // pass some extra data to ResultActivity
                            intent.putExtra(Constants.USER_NAME, username)
                            intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    // if an option is selected, get current question
                    val question = questionsList?.get(currentQuestion - 1)

                    // perform answer check
                    if (selectedOption != question!!.correctAnswer) {
                        // if wrong, style the wrong option
                        answerView(selectedOption, R.drawable.wrong_option_border_bg)
                    } else {
                        // if correct, increment correctAnswers
                        correctAnswers++
                    }
                    // style the correct option in either case
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    // set submit button's text to "FINISH" is the end is reached
                    // otherwise set it to "GO TO NEXT QUESTION"
                    if (currentQuestion == questionsList!!.size) {
                        btnSubmit?.text = "FINISH"
                    } else {
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    selectedOption = 0
                }
            }
        }
    }
}