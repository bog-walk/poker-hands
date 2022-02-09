package ui.util

import androidx.compose.runtime.*
import model.*

class PokerAppState(
    val hand1: MutableState<CardHand>,
    val hand2: MutableState<CardHand>,
    val chosenHand: MutableState<Winner>,
    val isCorrectChoice: MutableState<Boolean?>,
    val streak: MutableState<Int>
) {
    private var expectedWinner: Winner = findWinner(hand1.value to hand2.value)

    val shouldAllowDeal: Boolean
        get() = chosenHand.value != Winner.UNDECIDED

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
        chosenHand.value = Winner.UNDECIDED
        isCorrectChoice.value = null
    }
}

@Composable
fun rememberPokerAppState(): PokerAppState {
    val hands = deal()
    return remember {
        PokerAppState(
            mutableStateOf(hands.first),
            mutableStateOf(hands.second),
            mutableStateOf(Winner.UNDECIDED),
            mutableStateOf(null),
            mutableStateOf(0)
        )
    }
}




