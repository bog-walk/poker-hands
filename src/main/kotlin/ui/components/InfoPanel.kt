package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.Rank
import ui.style.PokerHandsTheme
import ui.style.cardPadding
import ui.style.componentPadding

@Composable
fun InfoPanel(highlighted: Set<Rank>, topRank: Rank?) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (rank in Rank.values()) {
            Text(
                text = rank.text,
                modifier = Modifier.padding(
                    horizontal = componentPadding,
                    vertical = cardPadding
                ),
                color = if (highlighted.isEmpty() || rank !in highlighted) {
                    PokerHandsTheme.colors.onError
                } else {
                    if (rank == topRank) {
                        PokerHandsTheme.colors.secondary
                    } else {
                        PokerHandsTheme.colors.error
                    }
                },
                style = PokerHandsTheme.typography.body1
            )
        }
    }
}

@Preview
@Composable
private fun InfoPanelPreview() {
    PokerHandsTheme {
        Row {
            InfoPanel(emptySet(), null)
            InfoPanel(setOf(Rank.FULL_HOUSE, Rank.ONE_PAIR), Rank.FULL_HOUSE)
            InfoPanel(setOf(Rank.FLUSH), Rank.FLUSH)
        }
    }
}