package fr.mastersid.stackoverflow.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import fr.mastersid.stackoverflow.viewModel.QuestionsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(questionsViewModel: QuestionsViewModel = viewModel()) {
    val questionsList by questionsViewModel.questionList.observeAsState(initial = emptyList())
    val refreshing by questionsViewModel.isUpdating.observeAsState(initial = false)
    val message by questionsViewModel.errorMessage.observeAsState()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(message) {
            if (message == "Network error" ) {
                snackbarHostState.showSnackbar(
                    "Network error", duration = SnackbarDuration.Short,
                    withDismissAction = true
                )
            } else if (message == " Request error") {
                snackbarHostState.showSnackbar(
                    "Request error", duration = SnackbarDuration.Short,
                    withDismissAction = true
                )
            } else {
                Log.d("erreur", message!!)
            }


    }
    Scaffold(
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                if (refreshing) {
                    LinearProgressIndicator();
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(questionsList) { question ->
                        Row {
                            Text(
                                text = question.title,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f),
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Italic
                            )
                            Spacer(modifier = Modifier.width(16.dp))

                            Text(
                                text = question.answerCount.toString(),
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        },
        floatingActionButton = {
            Column {
                UpdateQuestionButton(
                    updateQuestion = questionsViewModel::updateQuestions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            )
        }
    )
}



