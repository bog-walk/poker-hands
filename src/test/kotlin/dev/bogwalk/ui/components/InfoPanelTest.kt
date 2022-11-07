package dev.bogwalk.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import dev.bogwalk.ui.style.INFO_PANEL_TEST_TAG

internal class InfoPanelTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `InfoPanel creates 10 Text components`() {
        composeTestRule.setContent {
            InfoPanel(emptyList())
        }
        composeTestRule
            .onNodeWithTag(INFO_PANEL_TEST_TAG)
            .onChildren()
            .assertCountEquals(10)
    }
}