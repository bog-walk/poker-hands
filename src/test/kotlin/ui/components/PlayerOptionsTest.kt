package ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import model.Winner
import org.junit.Rule
import org.junit.Test
import ui.style.INFO_DESCRIPTION
import ui.style.PLAYER_BUTTON_TEXT
import ui.util.Choice

internal class PlayerOptionsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `PlayerOptions initial load only has enabled PickButton`() {
        composeTestRule.setContent {
            PlayerOptions(Winner.PLAYER1, Winner.UNDECIDED, Choice.NONE, {}, {})
        }
        composeTestRule
            .onNodeWithText("${PLAYER_BUTTON_TEXT}1")
            .assertExists("PickButton not found")
            .assertIsEnabled()
        composeTestRule
            .onNodeWithContentDescription(INFO_DESCRIPTION)
            .assertDoesNotExist()
    }

    @Test
    fun `PlayerOptions disables PickButton but doesn't show InfoButton if not picked`() {
        composeTestRule.setContent {
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER2, Choice.CORRECT, {}, {})
        }
        composeTestRule
            .onNodeWithText("${PLAYER_BUTTON_TEXT}1")
            .assertExists("PickButton not found")
            .assertIsNotEnabled()
        composeTestRule
            .onNodeWithContentDescription(INFO_DESCRIPTION)
            .assertDoesNotExist()
    }

    @Test
    fun `PlayerOptions disables PickButton and shows InfoButton if picked`() {
        composeTestRule.setContent {
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, Choice.CORRECT, {}, {})
        }
        composeTestRule
            .onNodeWithText("${PLAYER_BUTTON_TEXT}1")
            .assertExists("PickButton not found")
            .assertIsNotEnabled()
        composeTestRule
            .onNodeWithContentDescription(INFO_DESCRIPTION)
            .assertExists("InfoButton not found")
            .assertIsEnabled()
    }

    @Test
    fun `PlayerOptions disables InfoButton once clicked`() {
        composeTestRule.setContent {
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, Choice.CORRECT, {}, {})
        }
        composeTestRule
            .onNodeWithContentDescription(INFO_DESCRIPTION)
            .performClick()
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithContentDescription(INFO_DESCRIPTION)
            .assertIsNotEnabled()
    }
}