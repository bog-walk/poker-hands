package ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import ui.style.infoPanelTag

internal class InfoPanelTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `InfoPanel creates 10 Text components`() {
        composeTestRule.setContent {
            InfoPanel(emptyList())
        }
        composeTestRule
            .onNodeWithTag(infoPanelTag)
            .onChildren()
            .assertCountEquals(10)
    }
}