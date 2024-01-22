package fr.mastersid.stackoverflow.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.mastersid.stackoverflow.data.Question
import fr.mastersid.stackoverflow.ui.theme.StackOverflowTheme
import fr.mastersid.stackoverflow.viewModel.QuestionsViewModel


@Composable
fun QuestionsScreen(questionsViewModel: QuestionsViewModel= viewModel()){
    val questionsList by questionsViewModel.questionList.observeAsState(initial = emptyList() )
    val refreshing by questionsViewModel.isUpdating.observeAsState(initial = false)
        Box{
            if (refreshing){
                LinearProgressIndicator();
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize () ,
                contentPadding = PaddingValues (16.dp) ,
                verticalArrangement = Arrangement.spacedBy (16.dp)) {
                items(questionsList) {question->


                    Row {
                        Text(
                            text = question.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(8f),
                            fontSize = 20.sp,
                            fontStyle = FontStyle.Italic
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = question.answerCount.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f),
                            fontSize = 20.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            UpdateQuestionButton(updateQuestion = questionsViewModel::UpdateQuestions,
                modifier =Modifier.align(Alignment.BottomCenter)
                    . fillMaxWidth()
                    . padding (16.dp))
        }
}

