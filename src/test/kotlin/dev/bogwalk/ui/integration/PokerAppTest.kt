package dev.bogwalk.ui.integration

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.model.CardHand
import dev.bogwalk.model.TestDealer
import dev.bogwalk.model.TestPlay
import dev.bogwalk.model.getCard
import dev.bogwalk.poker_hands.generated.resources.*
import dev.bogwalk.ui.PokerHandsApp
import dev.bogwalk.ui.util.PokerAppState
import org.jetbrains.compose.resources.stringResource
import org.junit.BeforeClass
import org.junit.Rule
import kotlin.test.Test

// consider trying androidx.test.core.app.ApplicationProvider to access resources
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
        var dealButtonText = ""
        var playerButtonText = ""
        var infoCd = ""
        var playerTag = ""
        var infoTag = ""
        var cardTag = ""
        var streakCd = ""

        composeTestRule.setContent {
            dealButtonText = stringResource(Res.string.deal_button)
            playerButtonText = stringResource(Res.string.player_button)
            infoCd = stringResource(Res.string.info_cd)
            playerTag = stringResource(Res.string.player_test_tag)
            infoTag = stringResource(Res.string.info_panel_test_tag)
            cardTag = stringResource(Res.string.card_test_tag)
            streakCd = stringResource(Res.string.streak_cd)

            PokerHandsApp(testState)
        }

        // initial set up as expected
        composeTestRule.onNodeWithText(dealButtonText).assertIsNotEnabled()
        composeTestRule.onNodeWithText("${playerButtonText}1").assertIsEnabled()
        composeTestRule.onNodeWithText("${playerButtonText}2").assertIsEnabled()
        composeTestRule.onNodeWithContentDescription(infoCd).assertDoesNotExist()
        composeTestRule.onAllNodesWithTag(playerTag).assertCountEquals(2)
        composeTestRule.onAllNodesWithTag(infoTag).assertCountEquals(1)
        composeTestRule.onAllNodesWithTag(cardTag).assertCountEquals(10)
        composeTestRule
            .onNodeWithContentDescription(streakCd)
            .onSiblings()
            .assertAny(hasText("0"))

        // choose first winning hand correctly
        composeTestRule.onNodeWithText("${playerButtonText}1").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(dealButtonText).assertIsEnabled()
        composeTestRule.onNodeWithText("${playerButtonText}1").assertIsNotEnabled()
        composeTestRule.onNodeWithText("${playerButtonText}2").assertIsNotEnabled()
        composeTestRule.onNodeWithContentDescription(infoCd).assertIsEnabled()
        composeTestRule
            .onNodeWithContentDescription(streakCd)
            .onSiblings()
            .assertAny(hasTextExactly("1"))

        // deal new hands
        composeTestRule.onNodeWithText(dealButtonText).performClick()
        composeTestRule.waitForIdle()

        // choose second winning hand incorrectly
        composeTestRule.onNodeWithText("${playerButtonText}1").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithContentDescription(infoCd).assertIsEnabled()
        composeTestRule
            .onNodeWithContentDescription(streakCd)
            .onSiblings()
            .assertAny(hasTextExactly("0"))

        // request explanation for winning hand
        composeTestRule.onNodeWithContentDescription(infoCd).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithContentDescription(infoCd).assertIsNotEnabled()
        composeTestRule
            .onAllNodesWithTag(playerTag)
            .filterToOne(hasAnyDescendant(hasText("${playerButtonText}1")))
            .onChildren()
            .onFirst().assertContentDescriptionEquals("2 of HEARTS")
        composeTestRule
            .onAllNodesWithTag(playerTag)
            .filterToOne(hasAnyDescendant(hasText("${playerButtonText}2")))
            .onChildren()
            .onFirst().assertContentDescriptionEquals("3 of HEARTS")

        // deal new hands
        composeTestRule.onNodeWithText(dealButtonText).performClick()
        composeTestRule.waitForIdle()

        // tie (testDeck[2]) should be skipped
        composeTestRule
            .onAllNodesWithTag(playerTag)
            .filterToOne(hasAnyDescendant(hasText("${playerButtonText}1")))
            .onChildren()
            .onFirst().assertContentDescriptionEquals("8 of CLUBS")
        composeTestRule
            .onAllNodesWithTag(playerTag)
            .filterToOne(hasAnyDescendant(hasText("${playerButtonText}2")))
            .onChildren()
            .onFirst().assertContentDescriptionEquals("3 of HEARTS")
    }
}