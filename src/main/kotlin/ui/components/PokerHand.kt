package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.CardHand
import model.previewHand

@Composable
fun PokerHand(hand: CardHand) {
    Row(
        modifier = Modifier.fillMaxWidth().requiredWidth(650.dp),
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