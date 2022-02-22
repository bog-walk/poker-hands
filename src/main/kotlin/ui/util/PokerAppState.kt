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

    var hand1Highlights by mutableStateOf(List(5) { 0 })
    var hand2Highlights by mutableStateOf(List(5) { 0 })
    var infoPanelHighlights by mutableStateOf(List(10) { 0 })

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
        val info = generateRankInfo(hands).first()
        hand1Highlights = info.first
        hand2Highlights = info.second
        infoPanelHighlights = info.third
    }

    fun reset() {
        hands = deal()
        expectedWinner = findWinner(hands)
        hand1 = hands.first
        hand2 = hands.second
        chosenHand = Winner.UNDECIDED
        isCorrectChoice = Choice.NONE
        shouldAllowDeal = false
        hand1Highlights = List(5) { 0 }
        hand2Highlights = List(5) { 0 }
        infoPanelHighlights = List(10) { 0 }
    }
}