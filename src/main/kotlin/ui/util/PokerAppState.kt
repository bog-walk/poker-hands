package ui.util

import androidx.compose.runtime.*
import model.*

enum class Choice {
    NONE, CORRECT, INCORRECT
}

class PokerAppState : AppState {
    private var hands: Pair<CardHand, CardHand> = deal()
    override var expectedWinner = findWinner(hands.first, hands.second)

    override var hand1 by mutableStateOf(hands.first)
    override var hand2 by mutableStateOf(hands.second)

    override var chosenHand by mutableStateOf(Winner.UNDECIDED)
    override var isCorrectChoice by mutableStateOf(Choice.NONE)
    override var streak by mutableStateOf(0)
    override var shouldAllowDeal by mutableStateOf(false)

    override var hand1Highlights by mutableStateOf(emptyList<List<Int>>())
    override var hand2Highlights by mutableStateOf(emptyList<List<Int>>())
    override var infoPanelHighlights by mutableStateOf(emptyList<List<Int>>())

    override fun explainWinner() {
        val rankInfo = generateRankInfo(hands.first, hands.second)
        hand1Highlights = rankInfo.map { it.first }
        hand2Highlights = rankInfo.map { it.second }
        infoPanelHighlights = rankInfo.map { it.third }
    }

    override fun reset() {
        hands = deal()
        expectedWinner = findWinner(hands.first, hands.second)
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

interface AppState {
    var expectedWinner: Winner
    var hand1: CardHand
    var hand2: CardHand

    var chosenHand: Winner
    var isCorrectChoice: Choice
    var streak: Int
    var shouldAllowDeal: Boolean

    var hand1Highlights: List<List<Int>>
    var hand2Highlights: List<List<Int>>
    var infoPanelHighlights: List<List<Int>>

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

    fun explainWinner()
    fun reset()
}