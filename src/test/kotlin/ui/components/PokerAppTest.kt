package ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import model.CardHand
import model.getCard
import org.junit.BeforeClass
import org.junit.Rule
import ui.PokerHandsApp
import ui.style.dealButtonText
import ui.style.infoDescr
import ui.style.playerButtonText
import ui.style.playerRowTag
import kotlin.test.Test

internal class PokerAppTest {
    companion object {
        private lateinit var testDeck: List<Pair<CardHand, CardHand>>
        private lateinit var testDealer: TestDealer

        @BeforeClass
        @JvmStatic
        fun setUp() {
            val fakeHands1 = listOf(
                listOf("QS", "AC", "JC", "8H", "5S"),
                listOf("2H", "2D", "4C", "4D", "4S"),
                listOf("8C", "KC", "7C", "TC", "QC")
            ).map {
                CardHand(it.map { s -> getCard(s) })
            }
            val fakeHands2 = listOf(
                listOf("4C", "9D", "TD", "8S", "5H"),
                listOf("3H", "3D", "5C", "5D", "5S"),
                listOf("3H", "9H", "2H", "TH", "JH")
            ).map {
                CardHand(it.map { s -> getCard(s) })
            }
            testDeck = fakeHands1.zip(fakeHands2)
            testDealer = TestDealer(testDeck)
        }
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `PokerApp runs correctly`() {
        composeTestRule.setContent {
            PokerHandsApp()
        }
        composeTestRule.onNodeWithText(dealButtonText).assertIsNotEnabled()
        composeTestRule.onNodeWithText("${playerButtonText}1").assertIsEnabled()
        composeTestRule.onNodeWithText("${playerButtonText}2").assertIsEnabled()
        composeTestRule.onNodeWithContentDescription(infoDescr).assertDoesNotExist()
        composeTestRule.onAllNodesWithTag(playerRowTag).assertCountEquals(2)
        // choose first correctly
        composeTestRule.onNodeWithText("${playerButtonText}1").performClick()
    }
}









