package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.style.*

@Composable
fun DealButton(choiceMade: Boolean, onDealRequest: () -> Unit) {
    Row(
        modifier = Modifier.padding(componentPadding).requiredWidth(headerFooterWidth),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onDealRequest() },
            enabled = choiceMade
        ) {
            Text(
                text = dealButtonText,
                style = PokerHandsTheme.typography.button
            )
        }
    }
}

@Preview
@Composable
private fun DealButtonPreview() {
    PokerHandsTheme {
        Column {
            DealButton(false) { }
            DealButton(true) { }
        }
    }
}