package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
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
    Button(
        onClick = { onPlayerChosen(player) },
        modifier = Modifier.padding(componentPadding),
        enabled = chosenHand == null,
        colors = if (chosenHand != null && !isCorrectChoice!!) buttonColorError else buttonColor
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
            painterResource(infoIcon),
            contentDescription = infoDescr,
            tint = if (choseCorrectly) {
                PokerHandsTheme.colors.secondary
            } else {
                PokerHandsTheme.colors.error
            }
        )
    }
}

@Preview
@Composable
fun PlayerOptionsPreview() {
    PokerHandsTheme {
        PlayerOptions(Winner.PLAYER1, null, null) { }
    }
}



