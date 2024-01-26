package fr.mastersid.stackoverflow.data

data class ListQuestionJson(val items: List<QuestionJson>)

data class QuestionJson(
    val answer_count :Int,
    val title :String,
    val question_id: Int
)
