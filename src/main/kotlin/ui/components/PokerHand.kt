package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.CardHand
import model.previewHand

@Composable
fun PokerHand(hand: CardHand) {
    Row(
        modifier = Modifier.padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(5) {
            PokerCard(hand.cards[it])
        }
    }
}

@Preview
@Composable
fun PokerHandPreview() {
    PokerHand(previewHand)
}