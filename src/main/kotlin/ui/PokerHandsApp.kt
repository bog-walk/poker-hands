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
import ui.util.rememberPokerAppState

@Composable
@Preview
fun PokerHandsApp() {
    val pokerAppState = rememberPokerAppState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText()
        PlayerRow(
            Winner.PLAYER1,
            pokerAppState.hand1.value,
            pokerAppState.chosenHand.value,
            pokerAppState.isCorrectChoice.value
        ) {
            pokerAppState.assessChoice(it)
        }
        Row(
            modifier = Modifier.padding(componentPadding),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DealButton(pokerAppState.chosenHand.value != null) {
                pokerAppState.reset()
            }
            Spacer(modifier = Modifier.width(80.dp))
            HotStreak(pokerAppState.streak.value)
        }
        PlayerRow(
            Winner.PLAYER2,
            pokerAppState.hand2.value,
            pokerAppState.chosenHand.value,
            pokerAppState.isCorrectChoice.value
        ) {
            pokerAppState.assessChoice(it)
        }
    }
}

@Preview
@Composable
fun PokerHandsAppPreview() {
    PokerHandsTheme {
        PokerHandsApp()
    }
}