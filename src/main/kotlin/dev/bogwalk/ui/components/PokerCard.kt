package dev.bogwalk.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.testTag
import dev.bogwalk.model.Card
import dev.bogwalk.model.Suit
import dev.bogwalk.ui.style.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PokerCard(card: Card, highlight: Color) {
    Card(
        modifier = Modifier
            .requiredSize(cardWidth, cardHeight)
            .padding(cardPadding)
            .clearAndSetSemantics {
                contentDescription = "$card"
                testTag = CARD_TEST_TAG
            },
        shape = MaterialTheme.shapes.extraSmall,
        border = when (highlight) {
            Color.Transparent -> null
            else -> BorderStroke(cardBorder, highlight)
        },
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = when (card.suit) {
                Suit.CLUB, Suit.SPADE -> Color.Black
                Suit.DIAMOND, Suit.HEART -> Color.Red
            }
        )
    ) {
        // no point in separating to another composable to reduce recomposition, as Column is
        // an inline function that does not get its own recompose scope.
        // So if changed state causes recomposition, the invalidated scope will be the entire
        // Card content lambda, not just the Column content lambda.
        Column(
            modifier = Modifier.padding(cardPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = card.value,
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(intraSpacer))
            Icon(
                painter = painterResource(card.suit.svgPath),
                contentDescription = card.suit.description,
                modifier = Modifier.size(cardSuitSize)
            )
        }
    }
}

@Preview
@Composable
private fun PokerCardPreview() {
    val previewCards = listOf(
        Card(14, "A", Suit.HEART), Card(3, "3", Suit.DIAMOND),
        Card(13, "K", Suit.HEART), Card(10, "10", Suit.SPADE),
        Card(14, "A", Suit.CLUB),
    )
    PokerHandsTheme {
        Row {
            for ((i, sample) in previewCards.withIndex()) {
                PokerCard(sample, when (i) {
                    0 -> MaterialTheme.colorScheme.secondary
                    4 -> MaterialTheme.colorScheme.error
                    else -> Color.Transparent
                })
            }
        }
    }
}