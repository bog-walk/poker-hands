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

fun findWinner(hands: Pair<CardHand, CardHand>): Winner {
    val (player1Hand, player2Hand) = hands
    return when {
        player1Hand < player2Hand -> Winner.PLAYER2
        player1Hand > player2Hand -> Winner.PLAYER1
        else -> Winner.TIE
    }
}

fun generateRankInfo(
    hands: Pair<CardHand, CardHand>
): List<Triple<List<Int>, List<Int>, List<Int>>> {
    val rankInfo = mutableListOf<Triple<List<Int>, List<Int>, List<Int>>>()
    val allRanks = Rank.values()
    val (player1Hand, player2Hand) = hands
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
                    if (p1RPip > p2RPip) {
                        player1Info = getRankInfo(player1Hand, currentRank, p1RPip)
                        player2Info = getRankInfo(player2Hand, currentRank, p2RPip, false)
                        rankInfo.add(Triple(player1Info, player2Info, info))
                        break@outer
                    } else if (p2RPip > p1RPip) {
                        player1Info = getRankInfo(player1Hand, currentRank, p1RPip, false)
                        player2Info = getRankInfo(player2Hand, currentRank, p2RPip)
                        rankInfo.add(Triple(player1Info, player2Info, info))
                        break@outer
                    } else {
                        player1Info = getRankInfo(player1Hand, currentRank, p1RPip)
                        player2Info = getRankInfo(player2Hand, currentRank, p2RPip)
                        rankInfo.add(Triple(player1Info, player2Info, info))
                    }
                }
            } else {
                player1Info = getRankInfo(player1Hand, currentRank, p1R[0])
                player2Info = getRankInfo(player2Hand, currentRank, p2R[0])
                rankInfo.add(Triple(player1Info, player2Info, info))
            }
            r = when (r) {
                4, 5, 8 -> 0
                6 -> 3
                else -> r - 1
            }
        }
    }
    return rankInfo
}

private fun getRankInfo(
    hand: CardHand,
    rank: Rank,
    ranked: Int,
    isHigherRank: Boolean = true
): List<Int> {
    val x = if (isHigherRank) 1 else -1
    return when (rank) {
        Rank.HIGH_CARD, Rank.ONE_PAIR, Rank.THREE_KIND, Rank.FOUR_KIND -> {
            hand.cards.map { if (it.pip == ranked) x else 0 }
        }
        Rank.TWO_PAIR -> {
            // find non-pair card using bitwise xor
            val single = hand.cards.map(Card::pip).reduce { acc, i -> acc xor i }
            hand.cards.map { if (it.pip != single) x else 0 }
        }
        else -> List(5) { x }
    }
}