package dev.bogwalk.ui.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import dev.bogwalk.model.CardDealer
import dev.bogwalk.model.CardHand
import dev.bogwalk.model.Dealer
import dev.bogwalk.model.Winner

enum class Choice {
    NONE, CORRECT, INCORRECT
}

class PokerAppState(
    private val dealer: Dealer = CardDealer()
) {
    val hand1: CardHand
        get() = dealer.player1Hand
    val hand2: CardHand
        get() = dealer.player2Hand

    var chosenHand by mutableStateOf(Winner.UNDECIDED)
    var isCorrectChoice by mutableStateOf(Choice.NONE)
    var streak by mutableStateOf(0)
    var shouldAllowDeal by mutableStateOf(false)

    var hand1Highlights by mutableStateOf(emptyList<List<Int>>())
    var hand2Highlights by mutableStateOf(emptyList<List<Int>>())
    var infoPanelHighlights by mutableStateOf(emptyList<List<Int>>())

    fun assessChoice(player: Winner) {
        chosenHand = player
        isCorrectChoice = if (dealer.expectedWinner == chosenHand) {
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
        chosenHand = Winner.UNDECIDED
        isCorrectChoice = Choice.NONE
        shouldAllowDeal = false
        hand1Highlights = emptyList()
        hand2Highlights = emptyList()
        infoPanelHighlights = emptyList()
    }
}