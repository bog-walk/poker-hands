package model

import getTestResource
import org.junit.BeforeClass
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CardTest {
    /**
     * The createComposeRule() used in the test.ui package requires JUnit4, so a static approach
     * is instead taken to share a test resource, instead of using JUnit5's
     * @TestInstance(Lifecycle.PER_CLASS).
     */
    companion object {
        lateinit var entireDeck: List<Card>

        @BeforeClass
        @JvmStatic
        fun setUp() {
            entireDeck = getTestResource("src/test/resources/deck_cards").map { args ->
                Card(args[0].toInt(), args[1], Suit.valueOf(args[2]))
            }
        }
    }

    @Test
    fun `toString() correct`() {
        for (i in 9..52 step 13) {
            val expected1 = "9 of ${Suit.values()[i / 13]}S"
            val expected2 = "JACK of ${Suit.values()[i / 13]}S"
            assertEquals(expected1, entireDeck[i-2].toString())
            assertEquals(expected2, entireDeck[i].toString())
        }
    }

    @Test
    fun `getCard correct with all Integer inputs`() {
        for (number in 1..52) {
            if (number % 13 == 1) {
                assertEquals(entireDeck[number + 11], getCard(number))
            } else {
                assertEquals(entireDeck[number - 2], getCard(number))
            }
        }
    }

    @Test
    fun `getCard correct with all String inputs`() {
        val suits = Suit.values().map(Suit::abbreviation)
        val values = ('2'..'9').map(Char::toString) + Court.values().map(Court::abbreviation)
        val inputs = suits.flatMap { s -> values.map { v -> "$v$s" } }
        for ((i, input) in inputs.withIndex()) {
            assertEquals(entireDeck[i], getCard(input))
        }
    }
}