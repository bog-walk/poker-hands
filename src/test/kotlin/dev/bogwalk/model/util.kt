package dev.bogwalk.model

import java.io.File

typealias TestPlay = Pair<CardHand, CardHand>

fun getTestResource(
    filePath: String,
    lineTrim: CharArray = charArrayOf(' ', '\n'),
    lineSplit: String = " "
): List<List<String>> {
    val resource = mutableListOf<List<String>>()
    File(filePath).useLines { lines ->
        lines.forEach { line ->
            resource.add(line.trim(*lineTrim).split(lineSplit))
        }
    }
    return resource
}

fun TestPlay.validated(): Set<Card>? {
    val uniqueCards = first.cards.toSet() + second.cards.toSet()
    return if (uniqueCards.size == 10) uniqueCards else null
}

/**
 * Converts test resource hand ranking into a List<List<Int>>, as would be returned by the class
 * property CardHand.ranked.
 *
 * Input contains 10 comma-separated elements each representing a rank, with 0 indicating that the
 * rank is not present in the hand and should therefore become an empty list. The first input
 * element is wrapped in parentheses if more than 1 high cards are present in the hand.
 */
fun convertTestRanked(input: String): List<List<Int>> {
    val ranked = mutableListOf<List<Int>>()
    var highCards: MutableList<Int>? = null
    input.split(",").forEach {
        when {
            it.startsWith('(') -> highCards = mutableListOf(it.drop(1).toInt())
            it.endsWith(')') -> {
                highCards?.add(it.dropLast(1).toInt())
                highCards?.let { highs -> ranked.add(highs) }
                highCards = null
            }
            it == "0" -> ranked.add(emptyList())
            else -> highCards?.add(it.toInt()) ?: ranked.add(listOf(it.toInt()))
        }
    }
    return ranked
}

/**
 * Converts test resource into 2 CardHand instances for comparison.
 *
 * The final element in the input list represents the expected winner as an integer in [0, 2],
 * which corresponds to the ordinal positions of the enum class Winner.
 */
fun convertTestGame(input: List<String>): Pair<TestPlay, Winner> {
    val player1 = CardHand(input.slice(0..4).map(::getCard))
    val player2 = CardHand(input.slice(5..9).map(::getCard))
    val winner = Winner.entries[input.last().toInt()]
    return Pair(player1 to player2, winner)
}

/**
 * Converts test resource into 2 CardHand instances for generation of rank info.
 *
 * The 2nd component in the returned Pair represents the list of expected Triples of the
 * involved cards in each hand, as well as the involved rank titles, for each relevant rank in
 * a play.
 */
fun convertTestInfo(
    input: List<List<String>>
): List<Pair<TestPlay, List<RankInfo>>> {
    val sampleInfo = mutableListOf<Pair<TestPlay, List<RankInfo>>>()
    var i = 0
    while (i < input.size) {
        val player1 = CardHand(input[i].slice(0..4).map(::getCard))
        val player2 = CardHand(input[i].slice(5..9).map(::getCard))
        val info = mutableListOf<RankInfo>()
        for (j in input[i+1].indices) {
            val player1Ranks = input[i+1][j].split(",").map(String::toInt)
            val player2Ranks = input[i+2][j].split(",").map(String::toInt)
            val rankTitles = input[i+3][j].split(",").map(String::toInt)
            info.add(Triple(player1Ranks, player2Ranks, rankTitles))
        }
        sampleInfo.add(Pair(player1 to player2, info))
        i += 4
    }
    return sampleInfo
}