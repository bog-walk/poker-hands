package model

import checkValid
import convertTestGame
import getTestResource
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import kotlin.test.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CardDeckTest {
    private lateinit var samples: List<Triple<CardHand, CardHand, Int>>

    @BeforeAll
    fun setUP() {
        samples = getTestResource(
            "src/test/resources/poker_hands_sample"
        ).map(::convertTestGame)
    }

    @Test
    fun `deal does not return identical cards`() {
        val dealt = mutableSetOf<Card>()
        repeat(30) {
            deal().checkValid()?.let { cardsDealt ->
                assertNotNull(cardsDealt, "Invalid deal: $cardsDealt")
                dealt.addAll(cardsDealt)
            }
            if (dealt.size == 52) println("All cards seen at iteration ${it + 1}")
        }
    }

    @Test
    fun `samples resource does not have invalid hands or duplicate plays`() {
        val uniquePlays = mutableSetOf<Set<Card>>()
        for ((hand1, hand2, _) in samples) {
            (hand1 to hand2).checkValid()?.let { cardsDealt ->
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
            }
        }
        assertContentEquals(expectedWins, actualWins)
    }
}