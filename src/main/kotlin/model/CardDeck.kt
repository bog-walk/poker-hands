package model

typealias RankInfo = Triple<List<Int>, List<Int>, List<Int>>

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

fun findWinner(player1Hand: CardHand, player2Hand: CardHand): Winner {
    return when (player1Hand.compareTo(player2Hand)) {
        -1 -> Winner.PLAYER2
        1 -> Winner.PLAYER1
        else -> Winner.TIE
    }
}

/**
 * Returns a list of Triples representing a mapping of each CardHand (size 5), as well as the enum
 * class Rank list (size 10), for each rank involved in the assessment of the winning hand.
 *
 * This Triple will be used to map which Cards & Ranks should be highlighted (& how they should be
 * highlighted) to visualise each step in the assessment (and/or tie-breakers).
 *
 *      e.g. Hand1: ["3D", "6D", "7H", "QD", "QS"], Hand2: ["2D", "4C", "7D", "QC", "QH"]
 *      1st relevant rank = ONE_PAIR ->
 *      Triple([0,0,0,1,1], [0,0,0,1,1], [0,1,0,0,0,0,0,0,0,0])
 *      2nd relevant rank = HIGH_CARD ->
 *      Triple([0,0,1,0,0], [0,0,1,0,0], [1,0,0,0,0,0,0,0,0,0])
 *      3rd relevant rank = HIGH_CARD ->
 *      Triple([0,1,0,0,0], [0,-1,0,0,0], [1,0,0,0,0,0,0,0,0,0])
 */
fun generateRankInfo(player1Hand: CardHand, player2Hand: CardHand): List<RankInfo> {
    val rankInfo = mutableListOf<RankInfo>()
    val allRanks = Rank.values()
    val player1Ranked = player1Hand.ranked
    val player2Ranked = player2Hand.ranked
    var r = 9
    outer@while (r >= 0) {
        val p1R = player1Ranked[r]
        val p2R = player2Ranked[r]
        if (p1R.isEmpty() && p2R.isEmpty()) {
            r--
            continue@outer
        }
        val currentRank = allRanks[r]
        val info = MutableList(10) { 0 }.apply { this[r] = 1 }
        var player1Info: List<Int>
        var player2Info: List<Int>
        if (p1R.isEmpty()) {
            player2Info = getRankInfo(player2Hand, currentRank, p2R[0])
            val (p1Index, p1Top) = player1Ranked.withIndex().last { (i, rank) ->
                rank.isNotEmpty() && i < r
            }
            player1Info = getRankInfo(
                player1Hand, allRanks[p1Index], p1Top[0], false
            )
            info[p1Index] = -1
            rankInfo.add(Triple(player1Info, player2Info, info))
            break@outer
        } else if (p2R.isEmpty()) {
            player1Info = getRankInfo(player1Hand, currentRank, p1R[0])
            val (p2Index, p2Top) = player2Ranked.withIndex().last { (i, rank) ->
                rank.isNotEmpty() && i < r
            }
            player2Info = getRankInfo(
                player2Hand, allRanks[p2Index], p2Top[0], false
            )
            info[p2Index] = -1
            rankInfo.add(Triple(player1Info, player2Info, info))
            break@outer
        } else {
            if (r in 0..3 || r == 7) {
                for (i in p1R.indices) {
                    val p1RPip = p1R[i]
                    val p2RPip = p2R[i]
                    if (p1RPip == p2RPip) {
                        player1Info = getRankInfo(player1Hand, currentRank, p1RPip)
                        player2Info = getRankInfo(player2Hand, currentRank, p2RPip)
                        rankInfo.add(Triple(player1Info, player2Info, info))
                    } else {
                        player1Info = getRankInfo(
                            player1Hand, currentRank, p1RPip, p1RPip > p2RPip
                        )
                        player2Info = getRankInfo(
                            player2Hand, currentRank, p2RPip, p2RPip > p1RPip
                        )
                        rankInfo.add(Triple(player1Info, player2Info, info))
                        break@outer
                    }
                }
                r--
            } else {
                player1Info = getRankInfo(player1Hand, currentRank, p1R[0])
                player2Info = getRankInfo(player2Hand, currentRank, p2R[0])
                rankInfo.add(Triple(player1Info, player2Info, info))
                r = if (r == 6) 3 else 0 // else branch only caused by r in [4, 5, 8]
            }
        }
    }
    return rankInfo
}

/**
 * Returns a mapping of a CardHand to a list of Integers representing which Cards are involved in
 * a winning (1) or losing (-1) rank. Cards will be represented as 0 if not involved in the
 * current rank.
 *
 *      e.g. ["3D", "6D", "7H", "QD", "QS"] with current Rank as ONE_PAIR Queen ->
 *      [0, 0, 0, 1, 1] if this CardHand wins (or ties) this rank.
 *
 *      e.g. ["3D", "6D", "7H", "QD", "QS"] with current Rank as HIGH_CARD 7 ->
 *      [0, 0, -1, 0, 0] if this CardHand loses this rank.
 */
private fun getRankInfo(
    hand: CardHand,
    rank: Rank,
    rankHigh: Int,
    isHigherRank: Boolean = true
): List<Int> {
    val x = if (isHigherRank) 1 else -1
    return when (rank) {
        Rank.HIGH_CARD, Rank.ONE_PAIR, Rank.THREE_KIND, Rank.FOUR_KIND -> {
            hand.cards.map { if (it.pip == rankHigh) x else 0 }
        }
        Rank.TWO_PAIR -> {
            // find sole non-pair (repeated) card using bitwise XOR
            val single = hand.cards.map(Card::pip).reduce { acc, i -> acc xor i }
            hand.cards.map { if (it.pip != single) x else 0 }
        }
        // all remaining ranks involve all 5 cards
        else -> List(5) { x }
    }
}