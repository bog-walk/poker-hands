package ui.integration

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import model.CardHand
import model.TestDealer
import model.TestPlay
import model.getCard
import org.junit.BeforeClass
import org.junit.Rule
import ui.PokerHandsApp
import ui.style.*
import ui.util.PokerAppState
import kotlin.test.Test

internal class PokerAppTest {
    companion object {
        private lateinit var testDeck: List<TestPlay>
        private lateinit var testDealer: TestDealer
        private lateinit var testState: PokerAppState

        @BeforeClass
        @JvmStatic
        fun setUp() {
            val fakeHands1 = listOf(
                listOf("QS", "AC", "JC", "8H", "5S"),
                listOf("2H", "2D", "4C", "4D", "4S"),
                listOf("QH", "KH", "TH", "AH", "JH"),
                listOf("8C", "KC", "7C", "TC", "QC")
            ).map {
                CardHand(it.map { s -> getCard(s) })
            }
            val fakeHands2 = listOf(
                listOf("4C", "9D", "TD", "8S", "5H"),
                listOf("3H", "3D", "5C", "5D", "5S"),
                listOf("AC", "TC", "KC", "JC", "QC"),
                listOf("3H", "9H", "2H", "TH", "JH")
            ).map {
                CardHand(it.map { s -> getCard(s) })
            }
            testDeck = fakeHands1.zip(fakeHands2)
            testDealer = TestDealer(testDeck).apply { deal() }
            testState = PokerAppState(testDealer)
        }
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `PokerApp runs correctly`() {
        composeTestRule.setContent {
            PokerHandsApp(testState)
        }

        // initial set up as expected
        composeTestRule.onNodeWithText(DEAL_BUTTON_TEXT).assertIsNotEnabled()
        composeTestRule.onNodeWithText("${PLAYER_BUTTON_TEXT}1").assertIsEnabled()
        composeTestRule.onNodeWithText("${PLAYER_BUTTON_TEXT}2").assertIsEnabled()
        composeTestRule.onNodeWithContentDescription(INFO_DESCRIPTION).assertDoesNotExist()
        composeTestRule.onAllNodesWithTag(PLAYER_TEST_TAG).assertCountEquals(2)
        composeTestRule.onAllNodesWithTag(INFO_PANEL_TEST_TAG).assertCountEquals(1)
        composeTestRule.onAllNodesWithTag(CARD_TEST_TAG).assertCountEquals(10)
        composeTestRule
            .onNodeWithContentDescription(STREAK_DESCRIPTION)
            .onSiblings()
            .assertAny(hasText("0"))

        // choose first winning hand correctly
        composeTestRule.onNodeWithText("${PLAYER_BUTTON_TEXT}1").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(DEAL_BUTTON_TEXT).assertIsEnabled()
        composeTestRule.onNodeWithText("${PLAYER_BUTTON_TEXT}1").assertIsNotEnabled()
        composeTestRule.onNodeWithText("${PLAYER_BUTTON_TEXT}2").assertIsNotEnabled()
        composeTestRule.onNodeWithContentDescription(INFO_DESCRIPTION).assertIsEnabled()
        composeTestRule
            .onNodeWithContentDescription(STREAK_DESCRIPTION)
            .onSiblings()
            .assertAny(hasTextExactly("1"))

        // deal new hands
        composeTestRule.onNodeWithText(DEAL_BUTTON_TEXT).performClick()
        composeTestRule.waitForIdle()

        // choose second winning hand incorrectly
        composeTestRule.onNodeWithText("${PLAYER_BUTTON_TEXT}1").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithContentDescription(INFO_DESCRIPTION).assertIsEnabled()
        composeTestRule
            .onNodeWithContentDescription(STREAK_DESCRIPTION)
            .onSiblings()
            .assertAny(hasTextExactly("0"))

        // request explanation for winning hand
        composeTestRule.onNodeWithContentDescription(INFO_DESCRIPTION).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithContentDescription(INFO_DESCRIPTION).assertIsNotEnabled()
        composeTestRule
            .onAllNodesWithTag(PLAYER_TEST_TAG)
            .filterToOne(hasAnyDescendant(hasText("${PLAYER_BUTTON_TEXT}1")))
            .onChildren()
            .onFirst().assertContentDescriptionEquals("2 of HEARTS")
        composeTestRule
            .onAllNodesWithTag(PLAYER_TEST_TAG)
            .filterToOne(hasAnyDescendant(hasText("${PLAYER_BUTTON_TEXT}2")))
            .onChildren()
            .onFirst().assertContentDescriptionEquals("3 of HEARTS")

        // deal new hands
        composeTestRule.onNodeWithText(DEAL_BUTTON_TEXT).performClick()
        composeTestRule.waitForIdle()

        // tie (testDeck[2]) should be skipped
        composeTestRule
            .onAllNodesWithTag(PLAYER_TEST_TAG)
            .filterToOne(hasAnyDescendant(hasText("${PLAYER_BUTTON_TEXT}1")))
            .onChildren()
            .onFirst().assertContentDescriptionEquals("8 of CLUBS")
        composeTestRule
            .onAllNodesWithTag(PLAYER_TEST_TAG)
            .filterToOne(hasAnyDescendant(hasText("${PLAYER_BUTTON_TEXT}2")))
            .onChildren()
            .onFirst().assertContentDescriptionEquals("3 of HEARTS")
    }
}