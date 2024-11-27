package dev.bogwalk.model

import androidx.compose.runtime.Immutable

@Immutable
data class Card(val pip: Int, val value: String, val suit: Suit) {
    override fun toString(): String {
        return "${if (pip >= 10) Court.entries[pip-10].name else value} of ${suit.name}S"
    }
}

/**
 * Converts a random number in [1, 52] to a Card.
 *
 * The assigned pip is a numerical value in [2, 14], with Ace high, while the assigned value is a
 * string value in ["2", "10"] || {"J", "Q", "K", "A"}.
 *
 * The suit is assigned based on the assumption that all suit cards are grouped together in the
 * deck, with each group ordered as per the enum class Suit.
 *
 * @throws IllegalArgumentException if random number generator not constrained to 1..52.
 */
fun getCard(number: Int): Card {
    require(number in 1..52) { "Random number must be between 1 & 52 inclusive." }
    val (pip, value) = when (val numNorm = number % 13) {
        1 -> Pair(14, Court.ACE.abbreviation)
        11 -> Pair(numNorm, Court.JACK.abbreviation)
        12 -> Pair(numNorm, Court.QUEEN.abbreviation)
        0 -> Pair(13, Court.KING.abbreviation)
        else -> Pair(numNorm, numNorm.toString())
    }
    val suit = Suit.entries[(number - 1) / 13]
    return Card(pip, value, suit)
}

/**
 * Converts a 2-character input String to a Card.
 *
 * The assigned pip is a numerical value in [2, 14], with Ace high, while the assigned value is a
 * string value in ["2", "10"] || {"J", "Q", "K", "A"}.
 *
 * @throws IllegalArgumentException if input string does not match required pattern detailed below.
 */
fun getCard(input: String): Card {
    require(input.length == 2) { "Input string must be of 2 characters." }
    require(
        input.first() in '2'..'9' ||
                input.first().toString() in Court.entries.map(Court::abbreviation)
    ) { "First character must reference the pip." }
    require(
        input.last().toString() in Suit.entries.map(Suit::abbreviation)
    ) { "Second character must reference the suit." }
    val char1 = input.first()
    val pip = Court.ordOf(char1.toString())?.plus(10) ?: (char1.code - 48)
    val value = if (char1 == 'T') "10" else char1.toString()
    val suit = Suit.fromChar(input.last().toString())!!
    return Card(pip, value, suit)
}