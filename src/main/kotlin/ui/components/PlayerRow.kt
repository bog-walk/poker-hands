package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.CardHand
import model.Winner
import model.previewHand

@Composable
fun PlayerRow(player: Winner, hand: CardHand) {
    Row(
        modifier = Modifier.padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokerHand(hand)
        PlayerOptions(player)
    }
}

@Preview
@Composable
fun PlayerRowPreview() {
    PlayerRow(Winner.PLAYER1, previewHand)
}