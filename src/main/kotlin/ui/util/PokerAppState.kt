package ui.util

import androidx.compose.runtime.*
import model.*

enum class Choice {
    NONE, CORRECT, INCORRECT
}

class PokerAppState(
    private val dealer: Dealer = CardDealer()
) {
    var hand1 by mutableStateOf(dealer.player1Hand)
    var hand2 by mutableStateOf(dealer.player2Hand)
    var expectedWinner by mutableStateOf(dealer.expectedWinner)

    var chosenHand by mutableStateOf(Winner.UNDECIDED)
    var isCorrectChoice by mutableStateOf(Choice.NONE)
    var streak by mutableStateOf(0)
    var shouldAllowDeal by mutableStateOf(false)

    var hand1Highlights by mutableStateOf(emptyList<List<Int>>())
    var hand2Highlights by mutableStateOf(emptyList<List<Int>>())
    var infoPanelHighlights by mutableStateOf(emptyList<List<Int>>())

    fun assessChoice(player: Winner) {
        chosenHand = player
        isCorrectChoice = if (expectedWinner == chosenHand) {
            streak++
            Choice.CORRECT
        } else {
            streak = 0
            Choice.INCORRECT
        }
        shouldAllowDeal = true
    }

    fun explainWinner() {
        val rankInfo = dealer.generateRankInfo()
        hand1Highlights = rankInfo.map { it.first }
        hand2Highlights = rankInfo.map { it.second }
        infoPanelHighlights = rankInfo.map { it.third }
    }

    fun reset() {
        dealer.deal()
        hand1 = dealer.player1Hand
        hand2 = dealer.player2Hand
        expectedWinner = dealer.expectedWinner
        chosenHand = Winner.UNDECIDED
        isCorrectChoice = Choice.NONE
        shouldAllowDeal = false
        hand1Highlights = emptyList()
        hand2Highlights = emptyList()
        infoPanelHighlights = emptyList()
    }
}