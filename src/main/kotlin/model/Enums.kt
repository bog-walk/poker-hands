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

enum class Rank(val text: String) {
    HIGH_CARD("HIGH CARD"),
    ONE_PAIR("ONE PAIR"),
    TWO_PAIR("TWO PAIR"),
    THREE_KIND("THREE OF A KIND"),
    STRAIGHT("STRAIGHT"),
    FLUSH("FLUSH"),
    FULL_HOUSE("FULL HOUSE"),
    FOUR_KIND("FOUR OF A KIND"),
    STRAIGHT_FLUSH("STRAIGHT FLUSH"),
    ROYAL_FLUSH("ROYAL FLUSH")
}

enum class Winner {
    PLAYER1, PLAYER2, TIE, UNDECIDED
}