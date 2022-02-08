package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.CardHand
import model.Winner
import model.previewHand
import ui.style.componentPadding

@Composable
fun PlayerRow(player: Winner, hand: CardHand, onPlayerChosen: () -> Unit) {
    Row(
        modifier = Modifier.padding(componentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokerHand(hand)
        PlayerOptions(player, onPlayerChosen)
    }
}

@Preview
@Composable
fun PlayerRowPreview() {
    PlayerRow(Winner.PLAYER1, previewHand) { TODO() }
}