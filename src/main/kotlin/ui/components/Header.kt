package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ui.style.*

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
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h4
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