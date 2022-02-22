package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.CardHand
import model.Winner
import model.previewHand
import ui.style.PokerHandsTheme
import ui.style.componentPadding
import ui.style.playerRowPadding
import ui.util.Choice

@Composable
fun PlayerRow(
    player: Winner,
    hand: CardHand,
    highlighted: List<Int>,
    chosenHand: Winner,
    isCorrectChoice: Choice,
    onInfoRequest: () -> Unit,
    onPlayerChosen: (Winner) -> Unit
) {
    Row(
        modifier = Modifier.padding(
            start = playerRowPadding,
            top = componentPadding,
            bottom = componentPadding
        ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokerHand(hand, highlighted)
        PlayerOptions(player, chosenHand, isCorrectChoice, onInfoRequest, onPlayerChosen)
    }
}

@Preview
@Composable
private fun PlayerRowPreview() {
    PokerHandsTheme {
        Column {
            PlayerRow(
                Winner.PLAYER1, previewHand, listOf(0,0,0,0,0), Winner.UNDECIDED, Choice.NONE,
                {}, {}
            )
            PlayerRow(
                Winner.PLAYER1, previewHand, listOf(0,-1,0,0,0), Winner.PLAYER1, Choice.INCORRECT,
                {}, {}
            )
            PlayerRow(
                Winner.PLAYER2, previewHand, listOf(0,0,0,1,1), Winner.PLAYER2, Choice.CORRECT,
                {}, {}
            )
            PlayerRow(
                Winner.PLAYER1, previewHand, listOf(0,0,1,0,0), Winner.PLAYER2, Choice.CORRECT,
                {}, {}
            )
        }
    }
}