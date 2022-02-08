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
fun PlayerOptions(player: Winner, onPlayerChosen: () -> Unit) {
    Row(
        modifier = Modifier.padding(componentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PickButton(true, player, onPlayerChosen)
        Spacer(modifier = Modifier.width(componentPadding))
        InfoButton(true)
    }
}

@Composable
fun PickButton(isEnabled: Boolean, player: Winner, onPlayerChosen: () -> Unit) {
    PokerButton(
        isEnabled,
        "$playerButtonText${(player.ordinal + 1)}",
        onPlayerChosen
    )
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
    PlayerOptions(Winner.PLAYER1) { TODO() }
}



