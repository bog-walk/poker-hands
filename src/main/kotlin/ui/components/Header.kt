package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.style.PokerHandsTheme
import ui.style.componentPadding
import ui.style.headerAppText
import ui.style.headerSpacer

@Composable
fun Header(modifier: Modifier, streak: Int) {
    Row(
        modifier = modifier.padding(componentPadding),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderText()
        Spacer(modifier = Modifier.width(headerSpacer))
        HotStreak(streak)
    }
}

@Composable
fun HeaderText() {
    Text(
        text = headerAppText,
        modifier = Modifier.padding(componentPadding),
        style = PokerHandsTheme.typography.h4
    )
}

@Preview
@Composable
fun HeaderPreview() {
    PokerHandsTheme {
        Header(Modifier, 5)
    }
}