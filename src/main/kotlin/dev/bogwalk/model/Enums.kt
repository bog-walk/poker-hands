package dev.bogwalk.model

enum class Suit(
    val abbreviation: String,
    val description: String,
    val svgPath: String
) {
    CLUB("C", CLUB_DESCRIPTION, CLUB_SVG_PATH),
    DIAMOND("D", DIAMOND_DESCRIPTION, DIAMOND_SVG_PATH),
    HEART("H", HEART_DESCRIPTION, HEART_SVG_PATH),
    SPADE("S", SPADE_DESCRIPTION, SPADE_SVG_PATH);

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