package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.style.PokerHandsTheme
import ui.style.componentPadding
import ui.style.dealButtonText
import ui.style.outlineButtonBorder

@Composable
fun PokerButton(isEnabled: Boolean, buttonText: String, onButtonClick: () -> Unit) {
    OutlinedButton(
        onClick = { onButtonClick() },
        modifier = Modifier.padding(componentPadding),
        enabled = isEnabled,
        border = BorderStroke(outlineButtonBorder, PokerHandsTheme.lightColors.primary),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = PokerHandsTheme.lightColors.background,
            contentColor = PokerHandsTheme.lightColors.primary
        )
    ) {
        Text(
            text = buttonText,
            style = PokerHandsTheme.typography.button
        )
    }
}

@Composable
fun DealButton(onDealRequest: () -> Unit) {
    PokerButton(true, dealButtonText, onDealRequest)
}

@Preview
@Composable
fun DealButtonPreview() {
    PokerHandsTheme {
        DealButton { TODO() }
    }
}