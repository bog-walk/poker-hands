package ui.util

import androidx.compose.runtime.*
import model.Winner
import model.deal
import model.findWinner

class PokerAppState {
    var newHands = deal()
    var hand1 by remember { mutableStateOf(newHands.first) }
    var hand2 by remember { mutableStateOf(newHands.second) }
    var expectedWinner = findWinner(newHands)

    var chosenHand: Winner? by remember { mutableStateOf(null) }
    var isCorrectChoice: Boolean? by remember { mutableStateOf(null)}
    var streak by remember { mutableStateOf(0) }

    fun assessChoice(player: Winner) {
        chosenHand = player
        isCorrectChoice = chosenHand!! == expectedWinner
        if (isCorrectChoice == true) {
            streak++
        } else {
            streak = 0
        }
    }

    fun reset() {
        newHands = deal()
        hand1 = newHands.first
        hand2 = newHands.second
        expectedWinner = findWinner(newHands)
        chosenHand = null
        isCorrectChoice = null
    }
}