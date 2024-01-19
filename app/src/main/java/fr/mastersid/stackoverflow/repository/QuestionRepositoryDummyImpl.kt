package fr.mastersid.stackoverflow.repository

import fr.mastersid.stackoverflow.data.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class QuestionRepositoryDummyImpl @Inject constructor(): QuestionRepository {
    override val questionResponse: MutableStateFlow<QuestionResponse> =MutableStateFlow(
        QuestionResponse.Success(emptyList())
    )

    override suspend fun updateQuestionInfo() {
        questionResponse.emit(QuestionResponse.Pending)
        delay(1000)
        questionResponse.emit(QuestionResponse.Success(
            listOf(
                Question(1,"how are you?",12),
                Question(1,"first, Are you ready?",4),
                Question(1,"second, how old are youuuuuu?",15),

            )
        ))

    }
}