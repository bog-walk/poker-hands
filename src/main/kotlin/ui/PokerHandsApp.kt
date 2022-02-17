package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.Winner
import ui.components.*
import ui.style.PokerHandsTheme
import ui.util.rememberPokerAppState

@Composable
@Preview
fun PokerHandsApp() {
    val pokerAppState = rememberPokerAppState()
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoPanel(emptySet(), null)
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Header(
                pokerAppState.streak.value
            )
            PlayerRow(
                Winner.PLAYER1,
                pokerAppState.hand1.value,
                pokerAppState.chosenHand.value,
                pokerAppState.isCorrectChoice.value
            ) {
                pokerAppState.assessChoice(it)
            }
            PlayerRow(
                Winner.PLAYER2,
                pokerAppState.hand2.value,
                pokerAppState.chosenHand.value,
                pokerAppState.isCorrectChoice.value
            ) {
                pokerAppState.assessChoice(it)
            }
            DealButton(
                pokerAppState.shouldAllowDeal
            ) {
                pokerAppState.reset()
            }
        }
    }
}

@Preview
@Composable
private fun PokerHandsAppPreview() {
    PokerHandsTheme {
        PokerHandsApp()
    }
}