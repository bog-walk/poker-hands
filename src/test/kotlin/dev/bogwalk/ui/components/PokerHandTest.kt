package dev.bogwalk.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.model.Card
import dev.bogwalk.model.CardHand
import dev.bogwalk.model.Suit
import dev.bogwalk.poker_hands.generated.resources.Res
import dev.bogwalk.poker_hands.generated.resources.card_test_tag
import org.jetbrains.compose.resources.stringResource
import org.junit.Rule
import org.junit.Test

// consider trying androidx.test.core.app.ApplicationProvider to access resources
internal class PokerHandTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `PokerHand creates 5 PokerCard components`() {
        val previewHand = CardHand(
            listOf(
                Card(14, "A", Suit.HEART), Card(3, "3", Suit.DIAMOND),
                Card(13, "K", Suit.HEART), Card(10, "10", Suit.SPADE),
                Card(14, "A", Suit.CLUB),
            )
        )
        var cardTag = ""

        composeTestRule.setContent {
            cardTag = stringResource(Res.string.card_test_tag)

            PokerHand(previewHand, emptyList())
        }

        composeTestRule
            .onAllNodesWithTag(cardTag)
            .assertCountEquals(5)
            .assertAll(isNotFocusable() and !isSelectable())
    }
}