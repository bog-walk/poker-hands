package ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import model.Card
import model.CardHand
import model.Suit
import org.junit.Rule
import org.junit.Test
import ui.style.CARD_TEST_TAG

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
        composeTestRule.setContent {
            PokerHand(previewHand, emptyList())
        }
        composeTestRule
            .onAllNodes(hasTestTag(CARD_TEST_TAG))
            .assertCountEquals(5)
            .assertAll(isNotFocusable() and !isSelectable())
    }
}