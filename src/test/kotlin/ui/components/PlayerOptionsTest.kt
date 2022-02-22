package ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import model.Winner
import org.junit.Rule
import org.junit.Test
import ui.style.infoDescr
import ui.style.playerButtonText

internal class PlayerOptionsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `PlayerOptions initial load only has enabled PickButton`() {
        composeTestRule.setContent {
            PlayerOptions(Winner.PLAYER1, Winner.UNDECIDED, null, {}, {})
        }
        composeTestRule
            .onNodeWithText("${playerButtonText}1")
            .assertExists("PickButton not found")
            .assertIsEnabled()
        composeTestRule
            .onNodeWithContentDescription(infoDescr)
            .assertDoesNotExist()
    }

    @Test
    fun `PlayerOptions disables PickButton but doesn't show InfoButton if not picked`() {
        composeTestRule.setContent {
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER2, true, {}, {})
        }
        composeTestRule
            .onNodeWithText("${playerButtonText}1")
            .assertIsNotEnabled()
        composeTestRule
            .onNodeWithContentDescription(infoDescr)
            .assertDoesNotExist()
    }

    @Test
    fun `PlayerOptions disables PickButton and shows InfoButton if picked`() {
        composeTestRule.setContent {
            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, true, {}, {})
        }
        composeTestRule
            .onNodeWithText("${playerButtonText}1")
            .assertIsNotEnabled()
        composeTestRule
            .onNodeWithContentDescription(infoDescr)
            .assertExists()
            .assertIsEnabled()
    }
}