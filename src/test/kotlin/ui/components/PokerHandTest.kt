package ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import model.previewHand
import org.junit.Rule
import org.junit.Test
import ui.style.cardSemanticsDescr

internal class PokerHandTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `pokerHand creates 5 pokerCard components`() {
        composeTestRule.setContent {
            PokerHand(previewHand)
        }
        composeTestRule
            .onAllNodes(hasStateDescription(cardSemanticsDescr))
            .assertCountEquals(5)
            .assertAll(isNotFocusable() or !isSelectable())
    }
}