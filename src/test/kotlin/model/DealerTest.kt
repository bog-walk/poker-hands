package model

import TestPlay
import checkValid
import convertTestGame
import convertTestInfo
import getTestResource
import org.junit.BeforeClass
import ui.components.TestDealer
import kotlin.test.*

internal class DealerTest {
    /**
     * The createComposeRule() used in the test.ui package requires JUnit4, so a static approach
     * is instead taken to share test resources, instead of using JUnit5's
     * @TestInstance(Lifecycle.PER_CLASS).
     */
    companion object {
        lateinit var samples: List<Pair<TestPlay, Winner>>
        lateinit var samplePlays: List<TestPlay>
        lateinit var sampleInfo: List<Pair<TestPlay, List<RankInfo>>>
        lateinit var sampleInfoPlays: List<TestPlay>

        @BeforeClass
        @JvmStatic
        fun setUp() {
            samples = getTestResource(
                "src/test/resources/poker_hands_sample"
            ).map(::convertTestGame)
            samplePlays = samples.map { it.first }
            sampleInfo = convertTestInfo(
                getTestResource("src/test/resources/poker_hands_info")
            )
            sampleInfoPlays = sampleInfo.map { it.first }
        }
    }

    @Test
    fun `deal by PokerDealer does not return identical cards`() {
        val dealer = CardDealer()
        val dealt = mutableSetOf<Card>()
        var allCardsNotSeen = true
        repeat(30) {
            dealer.deal()
            val hands = dealer.player1Hand to dealer.player2Hand
            hands.checkValid().let { cardsDealt ->
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
        for ((hands, _) in samples) {
            hands.checkValid().let { cardsDealt ->
                assertNotNull(cardsDealt, "Invalid deal: ${hands.first} ${hands.second}")
                assertTrue("Duplicate found: $cardsDealt") {
                    uniquePlays.add(cardsDealt)
                }
            }
        }
    }

    @Test
    fun `findWinner correct for huge samples resource`() {
        val dealer = TestDealer(samplePlays)
        val expectedWins = intArrayOf(397, 641, 7) // player1, player2, tie
        val actualWins = IntArray(3)
        for ((i, sample) in samples.withIndex()) {
            dealer.deal()
            val winner = dealer.expectedWinner
            assertEquals(sample.second, winner, "Error at line ${i+1}")
            when (winner) {
                Winner.PLAYER1 -> actualWins[0]++
                Winner.PLAYER2 -> actualWins[1]++
                Winner.TIE -> actualWins[2]++
                Winner.UNDECIDED -> continue // sample winner will never be this value
            }
        }
        dealer.deal()
        assertContentEquals(expectedWins, actualWins)
    }

    @Test
    fun `generateRankInfo correct for sample info hands`() {
        val dealer = TestDealer(sampleInfoPlays)
        for (sample in sampleInfo) {
            dealer.deal()
            val expectedInfo = sample.second
            val actualInfo = dealer.generateRankInfo()
            assertEquals(
                expectedInfo.size, actualInfo.size, "Error with ${dealer.player1Hand.cards}"
            )
            for ((i, expected) in expectedInfo.withIndex()) {
                assertContentEquals(expected.first, actualInfo[i].first)
                assertContentEquals(expected.second, actualInfo[i].second)
                assertContentEquals(expected.third, actualInfo[i].third)
            }
        }
        dealer.deal()
    }
}