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
    fun `PokerHand creates 5 PokerCard components`() {
        composeTestRule.setContent {
            PokerHand(previewHand, emptyList())
        }
        composeTestRule
            .onAllNodes(hasStateDescription(cardSemanticsDescr))
            .assertCountEquals(5)
            .assertAll(isNotFocusable() or !isSelectable())
    }
}