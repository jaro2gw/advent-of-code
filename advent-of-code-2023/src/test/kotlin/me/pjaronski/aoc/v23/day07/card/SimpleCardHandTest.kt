package me.pjaronski.aoc.v23.day07.card

import me.pjaronski.aoc.v23.day07.card.CardHandType.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class SimpleCardHandTest {
    @TestFactory
    fun `should parse the card hand correctly`() = listOf(
        "23456 101" to Pair(HIGH_CARD, 101),
        "AA234 102" to Pair(ONE_PAIR, 102),
        "JJQQ2 103" to Pair(TWO_PAIR, 103),
        "TTT23 104" to Pair(THREE_KIND, 104),
        "44555 105" to Pair(FULL_HOUSE, 105),
        "KKKK2 106" to Pair(FOUR_KIND, 106),
        "AAAAA 107" to Pair(FIVE_KIND, 107),
    ).map { (line, expected) ->
        val (type, bid) = expected
        dynamicTest("should parse the card hand correctly [$line] -> $type") {
            val hand = SimpleCardHand.parse(line)

            assertEquals(bid, hand.bid)
            assertEquals(type, hand.type)
        }
    }
}
