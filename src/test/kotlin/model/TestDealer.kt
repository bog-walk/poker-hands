package model

class TestDealer(
    private val hands: List<TestPlay>
) : Dealer() {
    private val numOfPlays = hands.size
    var playIndex: Int = 0

    override fun deal() {
        if (playIndex < numOfPlays) {
            player1Hand = hands[playIndex].first
            player2Hand = hands[playIndex].second
            findWinner()
            playIndex++
            if (expectedWinner == Winner.TIE) {
                println("Skipped a complete tie")
                deal()
            }
        } else {
            println("No more test plays provided")
        }
    }
}