import model.Card
import model.CardHand
import model.getCard
import java.io.File

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

/**
 * Converts test resource hand ranking into a List<List<Int>>, as would be returned by
 * CardHand.ranked.
 *
 * Input contains 10 elements each representing a rank, with 0 indicating that the rank is not
 * present and should become an emptyList. The first element will be wrapped in parentheses if
 * more than 1 high cards are present in the hand.
 */
fun convertTestRanked(input: String): List<List<Int>> {
    val ranked = mutableListOf<List<Int>>()
    var highCards: MutableList<Int>? = null
    input.split(",").forEach {
        when {
            it.startsWith('(') -> highCards = mutableListOf(it.drop(1).toInt())
            it.endsWith(')') -> {
                highCards?.add(it.dropLast(1).toInt())
                highCards?.let { highs -> ranked.add(highs.toList()) }
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
 * which corresponds to the ordinal positions of the Winner enum class.
 */
fun convertTestGame(input: List<String>): Triple<CardHand, CardHand, Int> {
    val player1 = CardHand(input.slice(0..4).map(::getCard))
    val player2 = CardHand(input.slice(5..9).map(::getCard))
    val winner = input.last().toInt()
    return Triple(player1, player2, winner)
}

fun Pair<CardHand, CardHand>.checkValid(): Set<Card>? {
    val uniqueCards = first.cards.toSet() + second.cards.toSet()
    return if (uniqueCards.size == 10) uniqueCards else null
}