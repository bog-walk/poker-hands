package model

import androidx.compose.ui.graphics.Color

enum class Suit(
    val abbreviation: String,
    val description: String,
    val svgPath: String,
    val color: Color
) {
    CLUB("C", "Suit of clubs", "club.svg", Color.Black),
    DIAMOND("D", "Suit of diamonds", "diamond.svg", Color.Red),
    HEART("H", "Suit of hearts", "heart.svg", Color.Red),
    SPADE("S", "Suit of spades", "spade.svg", Color.Black);

    companion object {
        fun fromChar(char: String): Suit? = values().find { it.abbreviation == char }
    }
}

enum class Court(val abbreviation: String) {
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    companion object {
        fun ordOf(char: String): Int? = values().find { it.abbreviation == char }?.ordinal
    }
}

enum class Rank {
    HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_KIND,
    STRAIGHT_FLUSH, ROYAL_FLUSH
}

enum class Winner {
    PLAYER1, PLAYER2, TIE, UNDECIDED
}