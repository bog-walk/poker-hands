package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        backgroundColor = Color.White,
        contentColor = card.suit.color,
        elevation = cardElevation
    ) {
        // no point in separating to another composable to reduce recomposition, as Column is
        // an inline function that does not get its own recompose scope.
        // so if changed state causes recomposition, the invalidated scope will be the entire
        // Card content lambda, not just the Column content lambda.
        Column(
            modifier = Modifier.fillMaxSize().padding(cardPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = card.value,
                style = PokerHandsTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(intraCardSpacer))
            Icon(
                painter = painterResource(card.suit.svgPath),
                contentDescription = card.suit.description,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}

@Preview
@Composable
fun PokerCardPreview() {
    PokerHandsTheme {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (sample in previewCards) {
                PokerCard(sample)
            }
        }
    }
}