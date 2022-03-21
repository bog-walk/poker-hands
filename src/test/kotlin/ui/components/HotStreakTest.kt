package ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import ui.style.hotStreakDescr

internal class HotStreakTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `HotStreak loads correctly with no streak`() {
        composeTestRule.setContent {
            HotStreak(0)
        }
        composeTestRule
            .onNodeWithContentDescription(hotStreakDescr)
            .assertExists()
        composeTestRule
            .onNodeWithText("0")
            .assertExists()
    }

    @Test
    fun `HotStreak increments correctly`() {
        val streak = mutableStateOf(0)
        composeTestRule.setContent {
            HotStreak(streak.value)
        }
        composeTestRule
            .onNodeWithText(streak.value.toString())
            .assertExists()
        streak.value = 10
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithText(streak.value.toString())
            .assertExists()
    }
}



