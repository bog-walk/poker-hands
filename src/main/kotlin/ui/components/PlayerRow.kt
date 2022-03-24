package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import model.Card
import model.CardHand
import model.Suit
import model.Winner
import ui.style.PokerHandsTheme
import ui.style.componentPadding
import ui.style.playerRowPadding
import ui.style.playerRowTag
import ui.util.Choice

@Composable
fun PlayerRow(
    player: Winner,
    hand: CardHand,
    infoList: List<List<Int>>,
    chosenHand: Winner,
    isCorrectChoice: Choice,
    onInfoRequest: () -> Unit,
    onPlayerChosen: (Winner) -> Unit
) {
    Row(
        modifier = Modifier.padding(
            start = playerRowPadding,
            top = componentPadding,
            bottom = componentPadding
        ).testTag(playerRowTag),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokerHand(hand, infoList)
        PlayerOptions(
            player, chosenHand, isCorrectChoice, onInfoRequest, onPlayerChosen
        )
    }
}

@Preview
@Composable
private fun PlayerRowPreview() {
    val previewHand = CardHand(
        listOf(
            Card(14, "A", Suit.HEART), Card(3, "3", Suit.DIAMOND),
            Card(13, "K", Suit.HEART), Card(10, "10", Suit.SPADE),
            Card(14, "A", Suit.CLUB),
        )
    )

    PokerHandsTheme {
        Column {
            PlayerRow(
                Winner.PLAYER1, previewHand, emptyList(), Winner.UNDECIDED, Choice.NONE,
                {}, {}
            )
            PlayerRow(
                Winner.PLAYER1, previewHand, emptyList(), Winner.PLAYER1, Choice.INCORRECT,
                {}, {}
            )
            PlayerRow(
                Winner.PLAYER2, previewHand, emptyList(), Winner.PLAYER2, Choice.CORRECT,
                {}, {}
            )
            PlayerRow(
                Winner.PLAYER1, previewHand, emptyList(), Winner.PLAYER2, Choice.CORRECT,
                {}, {}
            )
        }
    }
}