package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import model.Winner
import ui.style.*

@Composable
fun PlayerOptions(
    player: Winner,
    chosenHand: Winner,
    isCorrectChoice: Boolean?,
    onInfoRequest: () -> Unit,
    onPlayerChosen: (Winner) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = componentPadding).requiredWidth(playerOptionsWidth),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PickButton(player, chosenHand, isCorrectChoice, onPlayerChosen)
        AnimatedVisibility(
            visible = chosenHand == player,
            modifier = Modifier.padding(start = componentPadding).zIndex(0f),
            enter = slideInHorizontally(
                animationSpec = tween(infoAnimDuration, easing = LinearOutSlowInEasing)
            ) { contentWidth ->
                -2 * contentWidth
            },
            exit = slideOutVertically(tween(infoAnimDuration, easing = FastOutLinearInEasing))
        ) {
            // kept getting NPE compared to original code
            // NEEDS TO BE FIXED
            isCorrectChoice?.let { InfoButton(isCorrectChoice, onInfoRequest) }
        }
        //if (chosenHand != Winner.UNDECIDED && chosenHand == player) {
            //Spacer(modifier = Modifier.width(componentPadding))
            //InfoButton(isCorrectChoice!!)
        //}
    }
}

@Composable
private fun PickButton(
    player: Winner,
    chosenHand: Winner,
    isCorrectChoice: Boolean?,
    onPlayerChosen: (Winner) -> Unit
) {
    Button(
        onClick = { onPlayerChosen(player) },
        modifier = Modifier.zIndex(2f),
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
private fun InfoButton(
    choseCorrectly: Boolean,
    onInfoRequest: () -> Unit
) {
    IconButton(
        onClick = { onInfoRequest() },
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
private fun PlayerOptionsPreview() {
    PokerHandsTheme {
        Column {
            PlayerOptions(Winner.PLAYER1, Winner.UNDECIDED, null, {}, {})
            PlayerOptions(Winner.PLAYER2, Winner.UNDECIDED, null, {}, {})
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, true, {}, {})
            PlayerOptions(Winner.PLAYER2, Winner.PLAYER1, true, {}, {})
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, false, {}, {})
            PlayerOptions(Winner.PLAYER2, Winner.PLAYER1, false, {}, {})
        }
    }
}