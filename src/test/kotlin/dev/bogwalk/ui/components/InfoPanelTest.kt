package dev.bogwalk.ui.components

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import dev.bogwalk.poker_hands.generated.resources.Res
import dev.bogwalk.poker_hands.generated.resources.info_panel_test_tag
import org.jetbrains.compose.resources.stringResource
import org.junit.Rule
import org.junit.Test

// consider trying androidx.test.core.app.ApplicationProvider to access resources
internal class InfoPanelTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `InfoPanel creates 10 Text components`() {
        var panelTag = ""

        composeTestRule.setContent {
            panelTag = stringResource(Res.string.info_panel_test_tag)

            InfoPanel(emptyList())
        }

        composeTestRule
            .onNodeWithTag(panelTag)
            .onChildren()
            .assertCountEquals(10)
    }
}