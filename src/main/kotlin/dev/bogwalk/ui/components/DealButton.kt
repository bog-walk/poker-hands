package dev.bogwalk.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.ui.style.DEAL_BUTTON_TEXT
import dev.bogwalk.ui.style.PokerHandsTheme
import dev.bogwalk.ui.style.componentPadding
import dev.bogwalk.ui.style.headerFooterWidth

@Composable
fun DealButton(
    choiceMade: Boolean,
    onDealRequest: () -> Unit
) {
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
                text = DEAL_BUTTON_TEXT,
                style = MaterialTheme.typography.labelMedium
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