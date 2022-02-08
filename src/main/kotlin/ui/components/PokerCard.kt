package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.stateDescription
import model.Card
import model.previewCards
import ui.style.*

@Composable
fun PokerCard(card: Card) {
    Card(
        modifier = Modifier
            .size(cardWidth, cardHeight)
            .padding(cardPadding)
            .clearAndSetSemantics {
                contentDescription = "$card"
                stateDescription = cardSemanticsDescr
            },
        contentColor = card.suit.color,
        elevation = cardElevation
    ) {
        PokerFace(card.suit.svgPath, card.suit.description, card.value)
    }
}

@Composable
fun PokerFace(suitPath: String, suitDescr: String, value: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(cardPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = PokerHandsTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(intraCardSpacer))
        Icon(
            painter = painterResource(suitPath),
            contentDescription = suitDescr,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Preview
@Composable
fun PokerCardPreview() {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        PokerHandsTheme {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (sample in previewCards) {
                    PokerCard(sample)
                }
            }
        }
        PokerHandsTheme(darkMode = true) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (sample in previewCards) {
                    PokerCard(sample)
                }
            }
        }
    }
}