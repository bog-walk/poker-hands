package dev.bogwalk.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.model.Winner
import dev.bogwalk.poker_hands.generated.resources.Res
import dev.bogwalk.poker_hands.generated.resources.info_cd
import dev.bogwalk.poker_hands.generated.resources.player_button
import dev.bogwalk.ui.util.Choice
import org.jetbrains.compose.resources.stringResource
import org.junit.Rule
import org.junit.Test

// consider trying androidx.test.core.app.ApplicationProvider to access resources
internal class PlayerOptionsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `PlayerOptions initial load only has enabled PickButton`() {
        var buttonText = ""
        var infoCD = ""

        composeTestRule.setContent {
            buttonText = stringResource(Res.string.player_button)
            infoCD = stringResource(Res.string.info_cd)

            PlayerOptions(Winner.PLAYER1, Winner.UNDECIDED, Choice.NONE, {}, {})
        }

        composeTestRule
            .onNodeWithText("${buttonText}1")
            .assertExists("PickButton not found")
            .assertIsEnabled()
        composeTestRule
            .onNodeWithContentDescription(infoCD)
            .assertDoesNotExist()
    }

    @Test
    fun `PlayerOptions disables PickButton but doesn't show InfoButton if not picked`() {
        var buttonText = ""
        var infoCD = ""

        composeTestRule.setContent {
            buttonText = stringResource(Res.string.player_button)
            infoCD = stringResource(Res.string.info_cd)

            PlayerOptions(Winner.PLAYER1, Winner.PLAYER2, Choice.CORRECT, {}, {})
        }

        composeTestRule
            .onNodeWithText("${buttonText}1")
            .assertExists("PickButton not found")
            .assertIsNotEnabled()
        composeTestRule
            .onNodeWithContentDescription(infoCD)
            .assertDoesNotExist()
    }

    @Test
    fun `PlayerOptions disables PickButton and shows InfoButton if picked`() {
        var buttonText = ""
        var infoCD = ""

        composeTestRule.setContent {
            buttonText = stringResource(Res.string.player_button)
            infoCD = stringResource(Res.string.info_cd)

            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, Choice.CORRECT, {}, {})
        }

        composeTestRule
            .onNodeWithText("${buttonText}1")
            .assertExists("PickButton not found")
            .assertIsNotEnabled()
        composeTestRule
            .onNodeWithContentDescription(infoCD)
            .assertExists("InfoButton not found")
            .assertIsEnabled()
    }

    @Test
    fun `PlayerOptions disables InfoButton once clicked`() {
        var infoCD = ""

        composeTestRule.setContent {
            infoCD = stringResource(Res.string.info_cd)

            PlayerOptions(Winner.PLAYER1, Winner.PLAYER1, Choice.CORRECT, {}, {})
        }

        composeTestRule
            .onNodeWithContentDescription(infoCD)
            .performClick()
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithContentDescription(infoCD)
            .assertIsNotEnabled()
    }
}