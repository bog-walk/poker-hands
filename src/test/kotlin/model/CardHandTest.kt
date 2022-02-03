package model

import convertTestRanked
import getTestResource
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CardHandTest {
    private lateinit var exampleHands: Map<Rank, CardHand>
    private lateinit var exampleNorms: Map<Rank, List<Int>>
    private lateinit var examplesRanked: Map<Rank, List<List<Int>>>

    @BeforeAll
    fun setUp() {
        val hands = getTestResource("src/test/resources/all_rank_hands")
        exampleHands = hands.associate { args ->
            Rank.valueOf(args.first()) to CardHand(args.slice(1..5).map(::getCard))
        }
        exampleNorms = hands.associate { args ->
            Rank.valueOf(args.first()) to args[6].map(Char::digitToInt)
        }
        examplesRanked = hands.associate { args ->
            Rank.valueOf(args.first()) to convertTestRanked(args[7])
        }
    }

    @Test
    fun `normalise correct for all rank examples`() {
        for ((rank, hand) in exampleHands) {
            assertContentEquals(exampleNorms[rank], hand.normalise())
        }
    }

    @Test
    fun `rankHand correct for all rank examples`() {
        for ((rank, hand) in exampleHands) {
            assertContentEquals(examplesRanked[rank], hand.rankHand())
        }
    }

    @Test
    fun `compareTo orders all rank examples correctly`() {
        val expected = Rank.values().reversed()
        val actual = exampleHands.keys.sortedDescending()
        assertContentEquals(expected, actual)
    }

    @Test
    fun `compareTo correct when hands have no rank ties`() {
        assertTrue { exampleHands[Rank.STRAIGHT]!! < exampleHands[Rank.FULL_HOUSE]!! }
        assertTrue { exampleHands[Rank.ROYAL_FLUSH]!! > exampleHands[Rank.STRAIGHT_FLUSH]!! }
        assertTrue { exampleHands[Rank.ONE_PAIR]!! < exampleHands[Rank.TWO_PAIR]!! }
    }

    @Test
    fun `compareTo correct when hands have 1 rank tie`() {
        val visitors = listOf(
            CardHand(listOf("2C", "3S", "8S", "8D", "TD").map(::getCard)),
            CardHand(listOf("2C", "5C", "7D", "8S", "QH").map(::getCard)),
            CardHand(listOf("3C", "3D", "3S", "9S", "9D").map(::getCard))
        )
        assertTrue { visitors[0] > exampleHands[Rank.ONE_PAIR]!! }
        assertTrue { visitors[1] < exampleHands[Rank.HIGH_CARD]!! }
        assertTrue { visitors[2] < exampleHands[Rank.FULL_HOUSE]!! }
    }

    @Test
    fun `compareTo correct when hands have 2 rank ties`() {
        val visitors = listOf(
            CardHand(listOf("7C", "AS", "KC", "7D", "TD").map(::getCard)),
            CardHand(listOf("QC", "AD", "JD", "7S", "2H").map(::getCard)),
            CardHand(listOf("8C", "8D", "3S", "9C", "9D").map(::getCard))
        )
        assertTrue { visitors[0] > exampleHands[Rank.ONE_PAIR]!! }
        assertTrue { visitors[1] < exampleHands[Rank.HIGH_CARD]!! }
        assertTrue { visitors[2] > exampleHands[Rank.TWO_PAIR]!! }
    }
}