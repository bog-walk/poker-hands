package dev.bogwalk.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.poker_hands.generated.resources.Res
import dev.bogwalk.poker_hands.generated.resources.header_text
import dev.bogwalk.ui.style.PokerHandsTheme
import dev.bogwalk.ui.style.componentPadding
import dev.bogwalk.ui.style.headerFooterWidth
import dev.bogwalk.ui.style.intraSpacer
import org.jetbrains.compose.resources.stringResource

@Composable
fun Header(streak: Int) {
    Row(
        modifier = Modifier.padding(componentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderText()
        Spacer(modifier = Modifier.width(intraSpacer))
        HotStreak(streak)
    }
}

@Composable
private fun HeaderText() {
    Text(
        text = stringResource(Res.string.header_text),
        modifier = Modifier.requiredWidth(headerFooterWidth),
        style = MaterialTheme.typography.displaySmall
    )
}

@Preview
@Composable
private fun HeaderPreview() {
    PokerHandsTheme {
        Column {
            Header(0)
            Header(5)
            Header(12)
            Header(100)
        }
    }
}