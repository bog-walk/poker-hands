package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.style.PokerHandsTheme
import ui.style.componentPadding
import ui.style.dealButtonText

@Composable
fun DealButton(modifier: Modifier, choiceMade: Boolean, onDealRequest: () -> Unit) {
    Button(
        onClick = { onDealRequest() },
        modifier = modifier.padding(componentPadding),
        enabled = choiceMade
    ) {
        Text(
            text = dealButtonText,
            style = PokerHandsTheme.typography.button
        )
    }
}

@Preview
@Composable
fun DealButtonPreview() {
    PokerHandsTheme {
        Row {
            DealButton(Modifier, false) { }
            DealButton(Modifier, true) { }
        }
    }
}