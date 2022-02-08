package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Winner
import model.previewHand
import ui.components.*

@Composable
@Preview
fun PokerHandsApp() {
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText()
            PlayerRow(Winner.PLAYER1, previewHand) { }
            Row(
                modifier = Modifier.padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DealButton { {} }
                Spacer(modifier = Modifier.width(80.dp))
                HotStreak()
            }
            PlayerRow(Winner.PLAYER2, previewHand) {  }
        }
    }
}

@Preview
@Composable
fun PokerHandsAppPreview() {
    PokerHandsApp()
}