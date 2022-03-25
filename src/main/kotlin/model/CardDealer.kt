package model

class CardDealer : Dealer() {
    init {
        deal()
    }

    override fun deal() {
        with(
            (1..52)
                .shuffled()
                .take(10)
                .map(::getCard)
        ) {
            player1Hand = CardHand(this.slice(0..4))
            player2Hand = CardHand(this.slice(5..9))
        }
        findWinner()
    }
}