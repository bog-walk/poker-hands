package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Winner
import ui.components.*
import ui.style.PokerHandsTheme
import ui.style.componentPadding
import ui.util.PokerAppState

@Composable
@Preview
fun PokerHandsApp() {
    val pokerAppState = PokerAppState()
    PokerHandsTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText()
            PlayerRow(
                Winner.PLAYER1,
                pokerAppState.hand1,
                pokerAppState.chosenHand,
                pokerAppState.isCorrectChoice
            ) {
                pokerAppState.assessChoice(it)
            }
            Row(
                modifier = Modifier.padding(componentPadding),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DealButton(pokerAppState.chosenHand != null) {
                    pokerAppState.reset()
                }
                Spacer(modifier = Modifier.width(80.dp))
                HotStreak(pokerAppState.streak)
            }
            PlayerRow(
                Winner.PLAYER2,
                pokerAppState.hand2,
                pokerAppState.chosenHand,
                pokerAppState.isCorrectChoice
            ) {
                pokerAppState.assessChoice(it)
            }
        }
    }
}

@Preview
@Composable
fun PokerHandsAppPreview() {
    PokerHandsApp()
}