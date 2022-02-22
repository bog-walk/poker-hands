package model

import checkValid
import convertTestGame
import convertTestInfo
import getTestResource
import org.junit.BeforeClass
import kotlin.test.*

internal class CardDeckTest {
    companion object {
        lateinit var samples: List<Triple<CardHand, CardHand, Int>>
        lateinit var sampleInfo: List<Triple<CardHand, CardHand, List<Triple<List<Int>, List<Int>, List<Int>>>>>

        @BeforeClass
        @JvmStatic
        fun setUP() {
            samples = getTestResource(
                "src/test/resources/poker_hands_sample"
            ).map(::convertTestGame)
            sampleInfo = convertTestInfo(getTestResource("src/test/resources/poker_hands_info"))
        }
    }

    @Test
    fun `deal does not return identical cards`() {
        val dealt = mutableSetOf<Card>()
        var allCardsNotSeen = true
        repeat(30) {
            deal().checkValid().let { cardsDealt ->
                assertNotNull(cardsDealt, "Invalid deal: $cardsDealt")
                dealt.addAll(cardsDealt)
            }
            if (allCardsNotSeen && dealt.size == 52) {
                println("All cards seen at iteration ${it + 1}")
                allCardsNotSeen = false
            }
        }
    }

    @Test
    fun `samples resource does not have invalid hands or duplicate plays`() {
        val uniquePlays = mutableSetOf<Set<Card>>()
        for ((hand1, hand2, _) in samples) {
            (hand1 to hand2).checkValid().let { cardsDealt ->
                assertNotNull(cardsDealt, "Invalid deal: $hand1 $hand2")
                assertTrue("Duplicate found: $cardsDealt") {
                    uniquePlays.add(cardsDealt)
                }
            }
        }
    }

    @Test
    fun `findWinner correct for huge samples resource`() {
        val winOptions = Winner.values()
        val expectedWins = intArrayOf(384, 634, 7) // player1, player2, tie
        val actualWins = IntArray(3)
        for ((i, sample) in samples.withIndex()) {
            val winner = findWinner(sample.first to sample.second)
            assertEquals(winOptions[sample.third], winner, "Error at line ${i+1}")
            when (winner) {
                Winner.PLAYER1 -> actualWins[0]++
                Winner.PLAYER2 -> actualWins[1]++
                Winner.TIE -> actualWins[2]++
                Winner.UNDECIDED -> continue // sample winner will never be this value
            }
        }
        assertContentEquals(expectedWins, actualWins)
    }

    @Test
    fun `generateRankInfo correct for sample info hands`() {
        for (sample in sampleInfo) {
            val hand1 = sample.first
            val hand2 = sample.second
            val expectedInfo = sample.third
            val actualInfo = generateRankInfo(hand1 to hand2)
            assertEquals(expectedInfo.size, actualInfo.size)
            for ((i, expected) in expectedInfo.withIndex()) {
                assertContentEquals(expected.first, actualInfo[i].first)
                assertContentEquals(expected.second, actualInfo[i].second)
                assertContentEquals(expected.third, actualInfo[i].third)
            }
        }
    }
}