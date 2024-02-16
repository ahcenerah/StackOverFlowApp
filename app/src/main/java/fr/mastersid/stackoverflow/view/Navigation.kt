package fr.mastersid.stackoverflow.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.stackoverflow.viewModel.QuestionsViewModel

@Composable
fun Navigation(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination: String = "QuestionsScreen"){
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("QuestionsScreen") {
                val viewModel = hiltViewModel<QuestionsViewModel>()
                QuestionsScreen(viewModel) {
                    navController.navigate("ResponsesScreen") }
            }
            composable("ResponsesScreen") {
                ResponsesScreen() }
        }


}
