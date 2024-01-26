package fr.mastersid.stackoverflow.repository

import android.util.Log
import fr.mastersid.stackoverflow.data.Question
import fr.mastersid.stackoverflow.webservice.StackOverflowService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val stackOverflowService: StackOverflowService ): QuestionRepository {
    override val questionResponse: MutableStateFlow<QuestionResponse> =MutableStateFlow(
        QuestionResponse.Success(emptyList())
    )

    override suspend fun updateQuestionInfo() {
        val list = stackOverflowService.getQuestionList()
        questionResponse.emit(QuestionResponse.Success(list))
    }
}