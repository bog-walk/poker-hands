package ui.util

import androidx.compose.runtime.*
import model.*

enum class Choice {
    NONE, CORRECT, INCORRECT
}

@Composable
fun rememberPokerAppState() = remember { PokerAppState(deal()) }

class PokerAppState(
    private var hands: Pair<CardHand, CardHand>
) {
    private var expectedWinner = findWinner(hands)
    var hand1 by mutableStateOf(hands.first)
    var hand2 by mutableStateOf(hands.second)

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
        val rankInfo = generateRankInfo(hands)
        hand1Highlights = rankInfo.map { it.first }
        hand2Highlights = rankInfo.map { it.second }
        infoPanelHighlights = rankInfo.map { it.third }
    }

    fun reset() {
        hands = deal()
        expectedWinner = findWinner(hands)
        hand1 = hands.first
        hand2 = hands.second
        chosenHand = Winner.UNDECIDED
        isCorrectChoice = Choice.NONE
        shouldAllowDeal = false
        hand1Highlights = emptyList()
        hand2Highlights = emptyList()
        infoPanelHighlights = emptyList()
    }
}