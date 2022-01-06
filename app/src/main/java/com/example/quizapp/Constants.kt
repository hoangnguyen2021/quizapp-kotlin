package com.example.quizapp

/**
 *  This class stores the app's data.
 */
object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val q1 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Uruguay",
            "Austria",
            "Fiji",
            1
        )
        val q2 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Lebanon",
            "South Africa",
            "Belgium",
            "Germany",
            4
        )
        val q3 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "China",
            "United States",
            "New Zealand",
            "Australia",
            3
        )
        val q4 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Chile",
            "Brazil",
            "Thailand",
            "Yemen",
            2
        )
        val q5 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Fiji",
            "Haiti",
            "Syria",
            "Papua New Guinea",
            1
        )
        val q6 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Kenya",
            "Jamaica",
            "Austria",
            "Australia",
            4
        )
        val q7 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Luxembourg",
            "Lithuania",
            "Belgium",
            "France",
            3
        )
        val q8 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Mexico",
            "Denmark",
            "Mongolia",
            "Egypt",
            2
        )
        val q9 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_india,
            "Russia",
            "Ghana",
            "Iran",
            "India",
            4
        )
        val q10 = Question(
            1,
            "What country does did flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Kuwait",
            "United Arab Emirates",
            "Sri Lanka",
            "Bangladesh",
            1
        )
        questionsList.add(q1)
        questionsList.add(q2)
        questionsList.add(q3)
        questionsList.add(q4)
        questionsList.add(q5)
        questionsList.add(q6)
        questionsList.add(q7)
        questionsList.add(q8)
        questionsList.add(q9)
        questionsList.add(q10)

        return questionsList
    }
}