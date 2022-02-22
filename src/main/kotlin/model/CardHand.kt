package model

import androidx.compose.runtime.Immutable

@Immutable
class CardHand(val cards: List<Card>) : Comparable<CardHand> {
    val ranked: List<List<Int>> = rankHand()

    /**
     * Normalise hand into a 15-element List, with index 0 representing the presence of a flush
     * (0 = false, 1 = true) and indices 1 to 14 representing the amount of cards with a pip
     * equal to the index.
     *
     * N.B. Aces are counted twice, as both high (index = 14) and low (index = 1).
     *
     * e.g. [0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 0, 0, 0, 1, 0] represents a hand with 2 fives, 1 six,
     * 1 seven, and 1 King, without a suit flush.
     */
    private fun normalise(): List<Int> {
        val normalised = MutableList(15) { 0 }
        if (cards.map(Card::suit).toSet().size == 1) normalised[0] = 1
        for (card in cards) {
            normalised[card.pip]++
            if (card.pip == 14) normalised[1]++
        }
        return normalised
    }

    private fun rankHand(): List<List<Int>> {
        val ranks = MutableList(10) { emptyList<Int>() }
        val cardCount = normalise()
        var streak = 0
        var pair = 0
        var triple = 0
        for (i in 14 downTo 2) {
            when (cardCount[i]) {
                0 -> { // no cards of this pip
                    streak = 0
                    continue
                }
                4 -> ranks[Rank.FOUR_KIND.ordinal] = listOf(i)
                3 -> {
                    ranks[Rank.THREE_KIND.ordinal] = listOf(i)
                    triple = i
                }
                2 -> {
                    if (pair > 0) {
                        // give the 2 pair ranking the higher value pair
                        ranks[Rank.TWO_PAIR.ordinal] = listOf(
                            maxOf(i, ranks[Rank.ONE_PAIR.ordinal].single())
                        )
                        ranks[Rank.ONE_PAIR.ordinal] = listOf(
                            minOf(i, ranks[Rank.ONE_PAIR.ordinal].single())
                        )
                    } else {
                        ranks[Rank.ONE_PAIR.ordinal] = listOf(i)
                        pair = i
                    }
                }
                1 -> ranks[Rank.HIGH_CARD.ordinal] += listOf(i)
            }
            streak++
            if (streak == 5 || streak == 4 && i == 2 && cardCount[1] > 0) {
                // low Ace straight has high card 5
                ranks[Rank.STRAIGHT.ordinal] = if (streak == 4) {
                    // ensure 14 not at front of high card list since Ace is low in this case
                    ranks[Rank.HIGH_CARD.ordinal] = ranks[Rank.HIGH_CARD.ordinal].drop(1) +
                            listOf(14)
                    listOf(5)
                } else {
                    listOf(i + 4)
                }
                if (cardCount[0] == 1) {
                    ranks[Rank.STRAIGHT_FLUSH.ordinal] = if (streak == 4) {
                        // low Ace straight flush has high card 5
                        listOf(5)
                    } else {
                        listOf(i + 4)
                    }
                    if (i == 10) ranks[Rank.ROYAL_FLUSH.ordinal] = listOf(14)
                }
                break // no further cards if streak achieved
            }
        }
        // give the full house ranking the higher 3 of a kind value
        if (triple > 0 && pair > 0) ranks[Rank.FULL_HOUSE.ordinal] = listOf(triple)
        // give flush ranking the highest card value, unless it's a low Ace straight flush
        if (cardCount[0] == 1) {
            ranks[Rank.FLUSH.ordinal] = ranks[Rank.STRAIGHT_FLUSH.ordinal].ifEmpty {
                listOf(ranks[Rank.HIGH_CARD.ordinal].maxOrNull()!!)
            }
        }
        return ranks
    }

    override fun compareTo(other: CardHand): Int {
        for (i in 9 downTo 1) {
            val thisRank = ranked[i]
            val otherRank = other.ranked[i]
            if (thisRank.isEmpty() && otherRank.isEmpty()) continue
            if (thisRank.isEmpty()) return -1
            if (otherRank.isEmpty()) return 1
            if (thisRank.single() == otherRank.single()) continue
            return if (thisRank.single() < otherRank.single()) -1 else 1
        }
        for (j in ranked[0].indices) {
            if (ranked[0][j] == other.ranked[0][j]) continue
            return if (ranked[0][j] < other.ranked[0][j]) -1 else 1
        }
        return 0
    }
}

val previewHand = CardHand(previewCards)