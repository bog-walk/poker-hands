package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import model.Winner
import ui.style.*

@Composable
fun PlayerOptions(
    player: Winner,
    chosenHand: Winner?,
    isCorrectChoice: Boolean?,
    onPlayerChosen: (Winner) -> Unit
) {
    Row(
        modifier = Modifier.padding(componentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PickButton(player, chosenHand, isCorrectChoice, onPlayerChosen)
        if (chosenHand != null && chosenHand == player) {
            Spacer(modifier = Modifier.width(componentPadding))
            InfoButton(isCorrectChoice!!)
        }
    }
}

@Composable
fun PickButton(
    player: Winner,
    chosenHand: Winner?,
    isCorrectChoice: Boolean?,
    onPlayerChosen: (Winner) -> Unit
) {
    val buttonColor = if (chosenHand == null) {
        ButtonDefaults.outlinedButtonColors(
            backgroundColor = PokerHandsTheme.lightColors.background,
            contentColor = PokerHandsTheme.lightColors.primary
        )
    } else {
        if (isCorrectChoice!!) {
            ButtonDefaults.outlinedButtonColors(
                backgroundColor = PokerHandsTheme.lightColors.secondary,
                contentColor = PokerHandsTheme.lightColors.onPrimary
            )
        } else {
            ButtonDefaults.outlinedButtonColors(
                backgroundColor = PokerHandsTheme.lightColors.error,
                contentColor = PokerHandsTheme.lightColors.onError
            )
        }
    }
    OutlinedButton(
        onClick = { onPlayerChosen(player) },
        modifier = Modifier.padding(componentPadding),
        enabled = chosenHand == null,
        border = BorderStroke(outlineButtonBorder, PokerHandsTheme.lightColors.primary),
        colors = buttonColor
    ) {
        Text(
            text = "$playerButtonText${(player.ordinal + 1)}",
            style = PokerHandsTheme.typography.button
        )
    }
}

@Composable
fun InfoButton(choseCorrectly: Boolean) {
    IconButton(
        onClick = {},
        modifier = Modifier.requiredSize(iconSize)
    ) {
        Icon(
            painterResource(infoOutlineIcon),
            contentDescription = infoOutlineDescr,
            tint = if (choseCorrectly) {
                PokerHandsTheme.lightColors.secondary
            } else {
                PokerHandsTheme.lightColors.error
            }
        )
    }
}

@Preview
@Composable
fun PlayerOptionsPreview() {
    PlayerOptions(Winner.PLAYER1, null, null) { TODO() }
}



