package fr.mastersid.stackoverflow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.stackoverflow.data.Question
import fr.mastersid.stackoverflow.repository.QuestionRepository
import fr.mastersid.stackoverflow.repository.QuestionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val questionRepository: QuestionRepository):
    ViewModel() {
    private val _questionList:MutableLiveData<List<Question>> = MutableLiveData(emptyList())
    val questionList : LiveData<List<Question>> =_questionList

    private val _isUpdating = MutableLiveData(false)
    val isUpdating : LiveData<Boolean> =_isUpdating

    init {
        viewModelScope.launch(Dispatchers.IO) {
            questionRepository.questionResponse.collect{
                response ->
                when(response){
                    is QuestionResponse.Pending -> _isUpdating.postValue(true)
                    is QuestionResponse.Success-> {
                        _questionList.postValue(response.list)
                        _isUpdating.postValue(false)
                    }
                }
            }
        }
    }
    fun UpdateQuestions(){
        viewModelScope.launch(Dispatchers.IO) {
            questionRepository.updateQuestionInfo()
        }
    }
}