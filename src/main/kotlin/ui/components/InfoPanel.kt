package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import model.Rank
import ui.style.*
import ui.util.produceHighlightState

@Composable
fun InfoPanel(infoList: List<List<Int>>) {
    val highlights = produceHighlightState(
        10, highlightDelay, infoList, PokerHandsTheme.colors.onError
    )

    Column(
        modifier = Modifier.fillMaxHeight().testTag(infoPanelTag),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (rank in Rank.values()) {
            key(rank.ordinal) {
                Text(
                    text = rank.text,
                    modifier = Modifier.padding(
                        horizontal = componentPadding,
                        vertical = cardPadding
                    ),
                    color = highlights.value[rank.ordinal],
                    style = PokerHandsTheme.typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
private fun InfoPanelPreview() {
    PokerHandsTheme {
        InfoPanel(emptyList())
    }
}