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

@Composable
fun PlayerRow(
    player: Winner,
    hand: CardHand,
    chosenHand: Winner,
    isCorrectChoice: Boolean?,
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
        PokerHand(hand)
        PlayerOptions(player, chosenHand, isCorrectChoice, onInfoRequest, onPlayerChosen)
    }
}

@Preview
@Composable
private fun PlayerRowPreview() {
    PokerHandsTheme {
        Column {
            PlayerRow(Winner.PLAYER1, previewHand, Winner.UNDECIDED, null, {}, {})
            PlayerRow(Winner.PLAYER1, previewHand, Winner.PLAYER1, false, {}, {})
            PlayerRow(Winner.PLAYER2, previewHand, Winner.PLAYER2, true, {}, {})
            PlayerRow(Winner.PLAYER1, previewHand, Winner.PLAYER2, true, {}, {})
        }
    }
}