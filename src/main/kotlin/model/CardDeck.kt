package model

enum class Winner {
    PLAYER1, PLAYER2, TIE
}

fun deal(): Pair<CardHand, CardHand> {
    var hand1: CardHand
    var hand2: CardHand
    with(
        (1..52)
            .shuffled()
            .take(10)
            .map(::getCard)
    ) {
        hand1 = CardHand(this.slice(0..4))
        hand2 = CardHand(this.slice(5..9))
    }
    return hand1 to hand2
}

fun play(): Winner {
    val (player1Hand, player2Hand) = deal()
    return when {
        player1Hand < player2Hand -> Winner.PLAYER2
        player1Hand > player2Hand -> Winner.PLAYER1
        else -> Winner.TIE
    }
}