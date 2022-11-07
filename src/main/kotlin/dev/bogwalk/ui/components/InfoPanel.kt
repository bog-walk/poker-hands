package dev.bogwalk.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import dev.bogwalk.model.Rank
import dev.bogwalk.ui.util.produceHighlightState
import dev.bogwalk.ui.style.*

@Composable
fun InfoPanel(
    highlightList: List<List<Int>>
) {
    val highlights = produceHighlightState(
        10, HIGHLIGHT_DELAY, highlightList, MaterialTheme.colorScheme.onError
    )

    Column(
        modifier = Modifier.testTag(INFO_PANEL_TEST_TAG).fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (rank in Rank.values()) {
            key(rank.ordinal) {
                Text(
                    text = rank.text,
                    modifier = Modifier.padding(
                        horizontal = componentPadding,
                        vertical = infoTextPadding
                    ),
                    color = highlights.value[rank.ordinal],
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun InfoPanelPreview() {
    PokerHandsTheme {
        Row {
            InfoPanel(emptyList())
            InfoPanel(listOf(listOf(-1, 0, 0, 0, 0, 1, 0, 0, 0, 0)))
        }
    }
}