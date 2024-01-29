package fr.mastersid.stackoverflow.repository

import android.util.Log
import fr.mastersid.stackoverflow.data.Question
import fr.mastersid.stackoverflow.data.QuestionDao
import fr.mastersid.stackoverflow.webservice.StackOverflowService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.Normalizer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepositoryImpl @Inject constructor(
    private val questionDao:QuestionDao,
    @CoroutineScopeIO private val coroutineScopeIO: CoroutineScope,
    private val stackOverflowService: StackOverflowService ): QuestionRepository {

    override val questionResponse: MutableStateFlow<QuestionResponse> =MutableStateFlow(
        QuestionResponse.Success(emptyList())
    )

    init {
        coroutineScopeIO.launch {
            questionDao.getQuestionListFlow().collect { list ->
                questionResponse.emit(QuestionResponse.Success(list))
            }
        }
    }

    override suspend fun updateQuestionInfo() {
        questionResponse.emit(QuestionResponse.Pending)
        val list = stackOverflowService.getQuestionList()
        questionDao.insertAll(list)
    }
}