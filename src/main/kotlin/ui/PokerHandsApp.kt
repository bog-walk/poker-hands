package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.Winner
import model.previewHand
import ui.components.DealButton
import ui.components.HeaderText
import ui.components.HotStreak
import ui.components.PlayerRow

@Composable
@Preview
fun PokerHandsApp() {
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText()
            PlayerRow(Winner.PLAYER1, previewHand)
            HotStreak(Modifier.align(Alignment.End))
            PlayerRow(Winner.PLAYER2, previewHand)
            DealButton()
        }
    }
}

@Preview
@Composable
fun PokerHandsAppPreview() {
    PokerHandsApp()
}