package fr.mastersid.stackoverflow.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.mastersid.stackoverflow.data.Question
import fr.mastersid.stackoverflow.ui.theme.StackOverflowTheme

@Composable
fun QuestionsScreen(){
    val questionsList = rememberSaveable{
        mutableStateOf(
            listOf(Question(1,"how are you",2),
                Question(2,"how are you",3),
                Question(5,"how are you how are you how are you how are you how are you how are you",6))
        )
    }

        Box{
            LazyColumn(
                modifier = Modifier.fillMaxSize () ,
                contentPadding = PaddingValues (16.dp) ,
                verticalArrangement = Arrangement.spacedBy (16.dp)) {
                items(questionsList.value) {question->


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
        }
}
@Preview( showBackground = true )
@Composable
fun QuestionScreenPreview(){
    StackOverflowTheme{
        QuestionsScreen()
    }

}
