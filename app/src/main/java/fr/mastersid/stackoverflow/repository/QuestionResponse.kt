package fr.mastersid.stackoverflow.repository

import fr.mastersid.stackoverflow.data.Question

sealed interface QuestionResponse{
    object  Pending : QuestionResponse

    @JvmInline
    value class  Success(val list: List<Question>):QuestionResponse
    @JvmInline
    value class Failure(val errorMessage: String) :QuestionResponse
}