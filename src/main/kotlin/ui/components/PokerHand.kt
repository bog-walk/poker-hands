package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.Card
import model.CardHand
import model.Suit
import ui.style.PokerHandsTheme
import ui.style.componentPadding
import ui.style.highlightDelay
import ui.util.produceHighlightState

@Composable
fun PokerHand(hand: CardHand, infoList: List<List<Int>>) {
    val highlights = produceHighlightState(5, highlightDelay, infoList)

    Row(
        modifier = Modifier.padding(componentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for ((i, card) in hand.cards.withIndex()) {
            key(i) {
                PokerCard(card, highlights.value[i])
            }
        }
    }
}

@Preview
@Composable
private fun PokerHandPreview() {
    val previewHand = CardHand(
        listOf(
            Card(14, "A", Suit.HEART), Card(3, "3", Suit.DIAMOND),
            Card(13, "K", Suit.HEART), Card(10, "10", Suit.SPADE),
            Card(14, "A", Suit.CLUB),
        )
    )

    PokerHandsTheme {
        Column {
            PokerHand(previewHand, emptyList())
            PokerHand(previewHand, emptyList())
        }
    }
}