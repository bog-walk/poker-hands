package model

import getTestResource
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import kotlin.test.Test
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CardTest {
    private lateinit var entireDeck: List<Card>

    @BeforeAll
    fun `setup deck`() {
        entireDeck = getTestResource("src/test/resources/deck_cards").map { line ->
            val args = line.split(" ")
            Card(args[0].toInt(), args[1], Suit.valueOf(args[2]))
        }
    }

    @Test
    fun `getCard correct with all Integer inputs`() {
        for (id in 1..52) {
            if (id % 13 == 1) {
                assertEquals(entireDeck[id + 11], getCard(id))
            } else {
                assertEquals(entireDeck[id - 2], getCard(id))
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