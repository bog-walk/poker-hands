package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.painterResource
import model.Winner
import ui.style.*

@Composable
fun PlayerOptions(
    player: Winner,
    chosenHand: Winner,
    isCorrectChoice: Boolean?,
    onPlayerChosen: (Winner) -> Unit
) {
    Row(
        modifier = Modifier.padding(componentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PickButton(player, chosenHand, isCorrectChoice, onPlayerChosen)
        if (chosenHand != Winner.UNDECIDED && chosenHand == player) {
            Spacer(modifier = Modifier.width(componentPadding))
            InfoButton(isCorrectChoice!!)
        }
    }
}

@Composable
fun PickButton(
    player: Winner,
    chosenHand: Winner,
    isCorrectChoice: Boolean?,
    onPlayerChosen: (Winner) -> Unit
) {
    Button(
        onClick = { onPlayerChosen(player) },
        enabled = chosenHand == Winner.UNDECIDED,
        colors = getButtonColors(player, chosenHand, isCorrectChoice)
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

@Composable
private fun getButtonColors(
    player: Winner,
    chosenHand: Winner,
    isCorrectChoice: Boolean? = null
): ButtonColors {
    return if (player != chosenHand) {
        ButtonDefaults.buttonColors()
    } else {
        if (isCorrectChoice!!) {
            ButtonDefaults.buttonColors(
                disabledBackgroundColor = PokerHandsTheme.colors.secondary.copy(alpha = 0.12f)
                    .compositeOver(PokerHandsTheme.colors.surface)
            )
        } else {
            ButtonDefaults.buttonColors(
                disabledBackgroundColor = PokerHandsTheme.colors.error.copy(alpha = 0.12f)
                    .compositeOver(PokerHandsTheme.colors.surface)
            )
        }
    }
}

@Preview
@Composable
fun PlayerOptionsPreview() {
    PokerHandsTheme {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            PlayerOptions(Winner.PLAYER1, Winner.UNDECIDED, null) { }
            PlayerOptions(Winner.PLAYER2, Winner.UNDECIDED, null) { }
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, true) { }
            PlayerOptions(Winner.PLAYER2, Winner.PLAYER1, true) { }
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, false) { }
            PlayerOptions(Winner.PLAYER2, Winner.PLAYER1, false) { }
        }
    }
}