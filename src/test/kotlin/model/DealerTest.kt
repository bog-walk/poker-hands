package model

import org.junit.BeforeClass
import kotlin.test.*

internal class DealerTest {
    /**
     * The createComposeRule() used in the test.ui package requires JUnit4, so a static approach
     * is instead taken to share test resources, instead of using JUnit5's
     * @TestInstance(Lifecycle.PER_CLASS).
     */
    companion object {
        private lateinit var samples: List<Pair<TestPlay, Winner>>
        private lateinit var samplePlays: List<TestPlay>
        private lateinit var sampleInfo: List<Pair<TestPlay, List<RankInfo>>>
        private lateinit var sampleInfoPlays: List<TestPlay>
        private lateinit var sampleTies: List<Pair<TestPlay, Winner>>
        private lateinit var sampleTiePlays: List<TestPlay>

        @BeforeClass
        @JvmStatic
        fun setUp() {
            samples = getTestResource(
                "src/test/resources/poker_hands_sample.txt"
            ).map(::convertTestGame)
            samplePlays = samples.map { it.first }
            sampleInfo = convertTestInfo(
                getTestResource("src/test/resources/poker_hands_info.txt")
            )
            sampleInfoPlays = sampleInfo.map { it.first }
            sampleTies = getTestResource(
                "src/test/resources/poker_hands_ties.txt"
            ).map(::convertTestGame)
            sampleTiePlays = sampleTies.map { it.first }
        }
    }

    @Test
    fun `deal by CardDealer does not return identical cards`() {
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
        val expectedWins = intArrayOf(397, 641) // player1, player2
        val actualWins = IntArray(2)
        for ((i, sample) in samples.withIndex()) {
            dealer.deal()
            val winner = dealer.expectedWinner
            assertEquals(sample.second, winner, "Error at line ${i+1}")
            when (winner) {
                Winner.PLAYER1 -> actualWins[0]++
                Winner.PLAYER2 -> actualWins[1]++
                else -> continue // sample winner will never be these value
            }
        }
        dealer.deal() // to ensure no further sample plays
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

    @Test
    fun `deal avoids composing CardHands that completely tie`() {
        val dealer = TestDealer(sampleTiePlays)
        while (dealer.playIndex < sampleTies.size) {
            dealer.deal()
            val winner = dealer.expectedWinner
            assertNotEquals(Winner.TIE, winner, "Error at line ${dealer.playIndex - 1}")
            val expected = sampleTies.indexOfFirst { it.second == winner }
            assertEquals(expected, dealer.playIndex - 1)
        }
        dealer.deal() // to ensure no further sample plays
    }
}