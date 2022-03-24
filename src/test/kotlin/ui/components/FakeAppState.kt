package ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import model.*
import ui.util.AppState
import ui.util.Choice

class FakeAppState(
    private val hands: List<Pair<CardHand, CardHand>>
) : AppState {
    var currentIndex = 0
    override var expectedWinner = findWinner(hands[currentIndex].first, hands[currentIndex].second)
    override var hand1 by mutableStateOf(hands[currentIndex].first)
    override var hand2 by mutableStateOf(hands[currentIndex].second)

    override var chosenHand by mutableStateOf(Winner.UNDECIDED)
    override var isCorrectChoice by mutableStateOf(Choice.NONE)
    override var streak by mutableStateOf(0)
    override var shouldAllowDeal by mutableStateOf(false)

    override var hand1Highlights by mutableStateOf(emptyList<List<Int>>())
    override var hand2Highlights by mutableStateOf(emptyList<List<Int>>())
    override var infoPanelHighlights by mutableStateOf(emptyList<List<Int>>())

    override fun explainWinner() {
        val rankInfo = generateRankInfo(hands[currentIndex].first, hands[currentIndex].second)
        hand1Highlights = rankInfo.map { it.first }
        hand2Highlights = rankInfo.map { it.second }
        infoPanelHighlights = rankInfo.map { it.third }
    }

    override fun reset() {
        currentIndex++
        expectedWinner = findWinner(hands[currentIndex].first, hands[currentIndex].second)
        hand1 = hands[currentIndex].first
        hand2 = hands[currentIndex].second
        chosenHand = Winner.UNDECIDED
        isCorrectChoice = Choice.NONE
        shouldAllowDeal = false
        hand1Highlights = emptyList()
        hand2Highlights = emptyList()
        infoPanelHighlights = emptyList()
    }

}