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
fun InfoPanel(highlighted: List<Int>) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val allRanks = Rank.values()
        for ((rank, switch) in highlighted.withIndex()) {
            Text(
                text = allRanks[rank].text,
                modifier = Modifier.padding(
                    horizontal = componentPadding,
                    vertical = cardPadding
                ),
                color = when (switch) {
                    -1 -> PokerHandsTheme.colors.error
                    1 -> PokerHandsTheme.colors.secondary
                    else -> PokerHandsTheme.colors.onError
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
            InfoPanel(listOf(0,0,0,0,0,0,0,0,0,0))
            InfoPanel(listOf(0,0,-1,0,0,0,0,0,0,1))
            InfoPanel(listOf(0,0,0,0,0,1,0,0,0,0))
        }
    }
}