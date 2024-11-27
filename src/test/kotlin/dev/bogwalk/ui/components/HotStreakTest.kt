package dev.bogwalk.ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import dev.bogwalk.poker_hands.generated.resources.Res
import dev.bogwalk.poker_hands.generated.resources.streak_cd
import org.jetbrains.compose.resources.stringResource
import org.junit.Rule
import org.junit.Test

// consider trying androidx.test.core.app.ApplicationProvider to access resources
internal class HotStreakTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `HotStreak loads correctly with no streak`() {
        var iconCD = ""

        composeTestRule.setContent {
            iconCD = stringResource(Res.string.streak_cd)

            HotStreak(0)
        }

        composeTestRule
            .onNodeWithContentDescription(iconCD)
            .assertExists("HotStreak icon not found")
        composeTestRule
            .onNodeWithText("0")
            .assertExists("HotStreak counter not found")
    }

    @Test
    fun `HotStreak increments correctly`() {
        val streak = mutableStateOf(0)

        composeTestRule.setContent {
            HotStreak(streak.value)
        }

        composeTestRule
            .onNodeWithText(streak.value.toString())
            .assertExists("Correct counter not found")
        streak.value = 10
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithText(streak.value.toString())
            .assertExists("Correct counter not found")
    }
}