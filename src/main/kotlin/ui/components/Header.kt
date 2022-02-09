package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.style.PokerHandsTheme
import ui.style.componentPadding
import ui.style.headerAppText

@Composable
fun HeaderText() {
    Text(
        text = headerAppText,
        modifier = Modifier.fillMaxWidth().padding(componentPadding),
        style = PokerHandsTheme.typography.h4
    )
}

@Preview
@Composable
fun HeaderPreview() {
    HeaderText()
}