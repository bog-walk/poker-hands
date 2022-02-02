package model

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