package dev.bogwalk.ui.components

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.bogwalk.poker_hands.generated.resources.Res
import dev.bogwalk.poker_hands.generated.resources.deal_button
import org.jetbrains.compose.resources.stringResource
import org.junit.Rule
import org.junit.Test

// consider trying androidx.test.core.app.ApplicationProvider to access resources
internal class DealButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `DealButton initially loads as disabled`() {
        var buttonText = ""

        composeTestRule.setContent {
            buttonText = stringResource(Res.string.deal_button)

            DealButton(false) { }
        }

        composeTestRule
            .onNodeWithText(buttonText)
            .assertExists("DealButton not found")
            .assertIsNotEnabled()
    }

    @Test
    fun `DealButton is enabled once winning hand is chosen`() {
        var buttonText = ""

        composeTestRule.setContent {
            buttonText = stringResource(Res.string.deal_button)

            DealButton(true) { }
        }

        composeTestRule
            .onNodeWithText(buttonText)
            .assertExists("DealButton not found")
            .assertIsEnabled()
    }
}