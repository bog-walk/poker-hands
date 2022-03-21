package ui.components

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import ui.style.dealButtonText

internal class DealButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `DealButton initially loads as disabled`() {
        composeTestRule.setContent {
            DealButton(false) { }
        }
        composeTestRule
            .onNodeWithText(dealButtonText)
            .assertExists("DealButton not found")
            .assertIsNotEnabled()
    }

    @Test
    fun `DealButton is enabled once winner is chosen`() {
        composeTestRule.setContent {
            DealButton(true) { }
        }
        composeTestRule
            .onNodeWithText(dealButtonText)
            .assertExists("DealButton not found")
            .assertIsEnabled()
    }
}