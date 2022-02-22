package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.CardHand
import model.previewHand
import ui.style.PokerHandsTheme
import ui.style.componentPadding

@Composable
fun PokerHand(hand: CardHand, highlighted: List<Int>) {
    Row(
        modifier = Modifier.padding(componentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(5) {
            PokerCard(hand.cards[it], highlighted[it])
        }
    }
}

@Preview
@Composable
private fun PokerHandPreview() {
    PokerHandsTheme {
        Column {
            PokerHand(previewHand, listOf(0,0,0,0,0))
            PokerHand(previewHand, listOf(1,0,-1,0,0))
        }
    }
}