package fr.mastersid.stackoverflow.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.mastersid.stackoverflow.data.CardItem

@Composable
fun ResponsesScreen () {
    val cardsList = listOf(
        CardItem(1, "Card 1", "Description 1"),
        CardItem(2, "Card 2", "Description 2"),
        CardItem(3, "Card 3", "Description 3"),
        CardItem(4, "Card 4", "Description 4"),
        CardItem(5, "Card 5", "Description 5")
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(cardsList) { card ->
            CardItem(card.score, card.author, card.description)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CardItem(score:Int, author: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$score",
                    color = Color.White, // Couleur du texte
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$author",
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$description"
            )
        }
    }
}

@Preview()
@Composable
fun PreviewResponsesScreen() {
    ResponsesScreen()
}