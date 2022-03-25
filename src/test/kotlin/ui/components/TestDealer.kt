package ui.components

import TestPlay
import model.Dealer

class TestDealer(
    private val hands: List<TestPlay>
) : Dealer() {
    private val numOfPlays = hands.size
    private var playIndex: Int = 0

    override fun deal() {
        if (playIndex < numOfPlays) {
            player1Hand = hands[playIndex].first
            player2Hand = hands[playIndex].second
            findWinner()
            playIndex++
        } else {
            println("No more test plays provided")
        }
    }
}