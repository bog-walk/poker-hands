package dev.bogwalk.ui.components

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.bogwalk.ui.components.DealButton
import org.junit.Rule
import org.junit.Test
import dev.bogwalk.ui.style.DEAL_BUTTON_TEXT

internal class DealButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `DealButton initially loads as disabled`() {
        composeTestRule.setContent {
            DealButton(false) { }
        }
        composeTestRule
            .onNodeWithText(DEAL_BUTTON_TEXT)
            .assertExists("DealButton not found")
            .assertIsNotEnabled()
    }

    @Test
    fun `DealButton is enabled once winning hand is chosen`() {
        composeTestRule.setContent {
            DealButton(true) { }
        }
        composeTestRule
            .onNodeWithText(DEAL_BUTTON_TEXT)
            .assertExists("DealButton not found")
            .assertIsEnabled()
    }
}