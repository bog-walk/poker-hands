package model

import convertTestGame
import getTestResource
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

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
        val expectedSize = 10
        val dealt = mutableSetOf<Card>()
        repeat(10) {
            val cardsDealt = deal().run {
                first.cards.toSet() + second.cards.toSet()
            }
            assertEquals(expectedSize, cardsDealt.size)
            dealt.addAll(cardsDealt)
            if (dealt.size == 52) println("All cards seen at iteration ${it + 1}")
            if (it == 9) println("Cards seen by last deal = ${dealt.size}")
        }
    }

    @Test
    fun `findWinner correct for huge samples resource`() {
        val winOptions = Winner.values()
        val expectedWins = intArrayOf(367, 633, 0) // player1, player2, tie
        var actualWins = IntArray(3)
        for ((i, sample) in samples.withIndex()) {
            val winner = findWinner(sample.first to sample.second)
            if (winner != winOptions[sample.third]) println("Error at line ${i+1}")
            assertEquals(winOptions[sample.third], winner)
            when (winner) {
                Winner.PLAYER1 -> actualWins[0]++
                Winner.PLAYER2 -> actualWins[1]++
                Winner.TIE -> actualWins[2]++
            }
        }
        assertContentEquals(expectedWins, actualWins)
    }
}