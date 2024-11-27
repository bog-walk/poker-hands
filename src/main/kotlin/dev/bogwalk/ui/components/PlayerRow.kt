package dev.bogwalk.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import dev.bogwalk.model.Card
import dev.bogwalk.model.CardHand
import dev.bogwalk.model.Suit
import dev.bogwalk.model.Winner
import dev.bogwalk.poker_hands.generated.resources.Res
import dev.bogwalk.poker_hands.generated.resources.player_test_tag
import dev.bogwalk.ui.style.PokerHandsTheme
import dev.bogwalk.ui.style.componentPadding
import dev.bogwalk.ui.style.playerRowPadding
import dev.bogwalk.ui.util.Choice
import org.jetbrains.compose.resources.stringResource

@Composable
fun PlayerRow(
    player: Winner,
    hand: CardHand,
    highlightList: List<List<Int>>,
    chosenHand: Winner,
    isCorrectChoice: Choice,
    onInfoRequest: () -> Unit,
    onPlayerChosen: (Winner) -> Unit
) {
    Row(
        modifier = Modifier
            .testTag(stringResource(Res.string.player_test_tag))
            .padding(
                start = playerRowPadding,
                top = componentPadding,
                bottom = componentPadding
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokerHand(hand, highlightList)
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