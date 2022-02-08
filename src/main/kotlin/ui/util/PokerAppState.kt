package ui.util

import androidx.compose.runtime.*
import model.CardHand
import model.Winner
import model.deal
import model.findWinner

@Composable
fun rememberPokerAppState() = remember { PokerAppState().apply { reset() } }

class PokerAppState {
    lateinit var hand1: MutableState<CardHand>
    lateinit var hand2: MutableState<CardHand>
    lateinit var chosenHand: MutableState<Winner?>
    lateinit var isCorrectChoice: MutableState<Boolean?>
    lateinit var streak: MutableState<Int>
    lateinit var expectedWinner: Winner

    fun assessChoice(player: Winner) {
        chosenHand.value = player
        isCorrectChoice.value = chosenHand.value == expectedWinner
        if (isCorrectChoice.value == true) {
            streak.value++
        } else {
            streak.value = 0
        }
    }

    fun reset() {
        val newHands = deal()
        hand1.value = newHands.first
        hand2.value = newHands.second
        expectedWinner = findWinner(newHands)
        chosenHand.value = null
        isCorrectChoice.value = null
    }
}