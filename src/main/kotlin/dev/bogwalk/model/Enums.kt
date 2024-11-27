package dev.bogwalk.model

enum class Suit(
    val abbreviation: String,
    val description: String
) {
    CLUB("C", CLUB_DESCRIPTION),
    DIAMOND("D", DIAMOND_DESCRIPTION),
    HEART("H", HEART_DESCRIPTION),
    SPADE("S", SPADE_DESCRIPTION);

    companion object {
        fun fromChar(char: String): Suit? = entries.find { it.abbreviation == char }
    }
}

enum class Court(val abbreviation: String) {
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    companion object {
        fun ordOf(char: String): Int? = entries.find { it.abbreviation == char }?.ordinal
    }
}

enum class Rank(val text: String) {
    HIGH_CARD(HIGH_CARD_TEXT),
    ONE_PAIR(ONE_PAIR_TEXT),
    TWO_PAIR(TWO_PAIR_TEXT),
    THREE_KIND(THREE_KIND_TEXT),
    STRAIGHT(STRAIGHT_TEXT),
    FLUSH(FLUSH_TEXT),
    FULL_HOUSE(FULL_HOUSE_TEXT),
    FOUR_KIND(FOUR_KIND_TEXT),
    STRAIGHT_FLUSH(STRAIGHT_FLUSH_TEXT),
    ROYAL_FLUSH(ROYAL_FLUSH_TEXT)
}

enum class Winner {
    PLAYER1, PLAYER2, TIE, UNDECIDED
}

// Suit semantics
private const val CLUB_DESCRIPTION = "Suit of clubs"
private const val DIAMOND_DESCRIPTION = "Suit of diamonds"
private const val HEART_DESCRIPTION = "Suit of hearts"
private const val SPADE_DESCRIPTION = "Suit of spades"

// Rank semantics
private const val HIGH_CARD_TEXT = "HIGH CARD"
private const val ONE_PAIR_TEXT = "ONE PAIR"
private const val TWO_PAIR_TEXT = "TWO PAIR"
private const val THREE_KIND_TEXT = "THREE OF A KIND"
private const val STRAIGHT_TEXT = "STRAIGHT"
private const val FLUSH_TEXT = "FLUSH"
private const val FULL_HOUSE_TEXT = "FULL HOUSE"
private const val FOUR_KIND_TEXT = "FOUR OF A KIND"
private const val STRAIGHT_FLUSH_TEXT = "STRAIGHT FLUSH"
private const val ROYAL_FLUSH_TEXT = "ROYAL FLUSH"