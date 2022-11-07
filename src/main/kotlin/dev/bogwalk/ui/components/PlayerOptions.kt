package dev.bogwalk.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import dev.bogwalk.model.Winner
import dev.bogwalk.ui.util.Choice
import dev.bogwalk.ui.style.*

@Composable
internal fun PlayerOptions(
    player: Winner,
    chosenHand: Winner,
    isCorrectChoice: Choice,
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
            modifier = Modifier.zIndex(0f),
            enter = slideInHorizontally(
                animationSpec = tween(
                    durationMillis = INFO_ENTER_ANIM_DURATION, easing = LinearOutSlowInEasing
                )
            ) { contentWidth ->
                -2 * contentWidth
            }
        ) {
            InfoButton(isCorrectChoice, onInfoRequest)
        }
    }
}

@Composable
private fun PickButton(
    player: Winner,
    chosenHand: Winner,
    isCorrectChoice: Choice,
    onPlayerChosen: (Winner) -> Unit
) {
    Button(
        onClick = { onPlayerChosen(player) },
        modifier = Modifier.zIndex(2f),
        enabled = chosenHand == Winner.UNDECIDED,
        colors = getButtonColors(player, chosenHand, isCorrectChoice)
    ) {
        Text(
            text = "$PLAYER_BUTTON_TEXT${(player.ordinal + 1)}",
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun getButtonColors(
    player: Winner,
    chosenHand: Winner,
    isCorrectChoice: Choice
): ButtonColors {
    return if (player != chosenHand) {
        // user either has not yet picked a winning hand (enabled button defaults)
        // or user picked the other hand (disabled button defaults)
        ButtonDefaults.buttonColors()
    } else {
        if (isCorrectChoice == Choice.CORRECT) {
            ButtonDefaults.buttonColors(
                disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f)
                    .compositeOver(MaterialTheme.colorScheme.surface)
            )
        } else {
            ButtonDefaults.buttonColors(
                disabledContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.12f)
                    .compositeOver(MaterialTheme.colorScheme.surface)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InfoButton(
    choseCorrectly: Choice,
    onInfoRequest: () -> Unit
) {
    // InfoButton can only be clicked once to produce highlighted states
    var alreadyClicked by remember { mutableStateOf(false) }

    FilledIconButton(
        onClick = {
            alreadyClicked = true
            onInfoRequest()
        },
        modifier = Modifier.padding(start = componentPadding),
        enabled = !alreadyClicked,
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = when (choseCorrectly) {
                Choice.CORRECT -> MaterialTheme.colorScheme.secondary // green
                Choice.INCORRECT -> MaterialTheme.colorScheme.error // red
                Choice.NONE -> MaterialTheme.colorScheme.surface // will not be visible
            },
            contentColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Icon(
            painter = painterResource(INFO_ICON),
            contentDescription = INFO_DESCRIPTION,
            modifier = Modifier.requiredSize(playerRowPadding)
        )
    }
}

@Preview
@Composable
private fun PlayerOptionsPreview() {
    PokerHandsTheme {
        Column {
            PlayerOptions(Winner.PLAYER1, Winner.UNDECIDED, Choice.NONE, {}, {})
            PlayerOptions(Winner.PLAYER2, Winner.UNDECIDED, Choice.NONE, {}, {})
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, Choice.CORRECT, {}, {})
            PlayerOptions(Winner.PLAYER2, Winner.PLAYER1, Choice.CORRECT, {}, {})
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, Choice.INCORRECT, {}, {})
            PlayerOptions(Winner.PLAYER2, Winner.PLAYER1, Choice.INCORRECT, {}, {})
        }
    }
}