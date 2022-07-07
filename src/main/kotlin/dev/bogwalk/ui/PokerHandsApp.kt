package dev.bogwalk.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.model.Winner
import dev.bogwalk.ui.components.DealButton
import dev.bogwalk.ui.components.Header
import dev.bogwalk.ui.components.InfoPanel
import dev.bogwalk.ui.components.PlayerRow
import dev.bogwalk.ui.style.PokerHandsTheme
import dev.bogwalk.ui.util.PokerAppState

@Composable
@Preview
fun PokerHandsApp(state: PokerAppState = PokerAppState()) {
    val pokerAppState = remember { state }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoPanel(pokerAppState.infoPanelHighlights)
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Header(
                pokerAppState.streak
            )
            PlayerRow(
                Winner.PLAYER1,
                pokerAppState.hand1,
                pokerAppState.hand1Highlights,
                pokerAppState.chosenHand,
                pokerAppState.isCorrectChoice,
                pokerAppState::explainWinner
            ) {
                pokerAppState.assessChoice(it)
            }
            PlayerRow(
                Winner.PLAYER2,
                pokerAppState.hand2,
                pokerAppState.hand2Highlights,
                pokerAppState.chosenHand,
                pokerAppState.isCorrectChoice,
                pokerAppState::explainWinner
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