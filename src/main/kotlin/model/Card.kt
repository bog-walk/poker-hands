package model

import androidx.compose.runtime.Immutable

enum class Suit(val abbreviation: String) {
    CLUB("C"),
    DIAMOND("D"),
    HEART("H"),
    SPADE("S");

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

@Immutable
data class Card(val pip: Int, val value: String, val suit: Suit)

/**
 * Convert a random number in [1, 52] to a Card.
 *
 * The assigned pip is a numerical value in [2, 14], with Ace high, while the assigned value is a
 * string value in ["2", "10"] || {"J", "Q", "K", "A"}.
 */
fun getCard(id: Int): Card {
    val (pip, value) = when (val idNorm = id % 13) {
        1 -> Pair(14, Court.ACE.abbreviation)
        11 -> Pair(idNorm, Court.JACK.abbreviation)
        12 -> Pair(idNorm, Court.QUEEN.abbreviation)
        0 -> Pair(13, Court.KING.abbreviation)
        else -> Pair(idNorm, (idNorm).toString())
    }
    val suit = Suit.values()[(id - 1) / 13]
    return Card(pip, value, suit)
}

/**
 * Convert a 2-character input String to a Card.
 *
 * The assigned pip is a numerical value in [2, 14], with Ace high, while the assigned value is a
 * string value in ["2", "10"] || {"J", "Q", "K", "A"}.
 */
fun getCard(input: String): Card {
    val char1 = input.first()
    val pip = Court.ordOf(char1.toString())?.plus(10) ?: (char1.code - 48)
    val value = if (char1 == 'T') "10" else char1.toString()
    val suit = Suit.fromChar(input.last().toString())!!
    return Card(pip, value, suit)
}