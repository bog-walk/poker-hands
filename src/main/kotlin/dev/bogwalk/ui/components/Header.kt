package dev.bogwalk.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.ui.style.*

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
        text = HEADER_TEXT,
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