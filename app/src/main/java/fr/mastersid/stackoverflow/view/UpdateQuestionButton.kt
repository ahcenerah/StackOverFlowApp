package fr.mastersid.stackoverflow.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UpdateQuestionButton(updateQuestion :()-> Unit, modifier: Modifier){
    Button (
        onClick = updateQuestion,
        modifier = modifier
    ) {
        Text (" Update Question ")
    }
}