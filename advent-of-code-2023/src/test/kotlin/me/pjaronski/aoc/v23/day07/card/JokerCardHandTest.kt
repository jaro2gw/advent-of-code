package me.pjaronski.aoc.v23.day07.card

import me.pjaronski.aoc.v23.day07.card.CardHandType.FIVE_KIND
import me.pjaronski.aoc.v23.day07.card.CardHandType.FOUR_KIND
import me.pjaronski.aoc.v23.day07.card.CardHandType.FULL_HOUSE
import me.pjaronski.aoc.v23.day07.card.CardHandType.HIGH_CARD
import me.pjaronski.aoc.v23.day07.card.CardHandType.ONE_PAIR
import me.pjaronski.aoc.v23.day07.card.CardHandType.THREE_KIND
import me.pjaronski.aoc.v23.day07.card.CardHandType.TWO_PAIR
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class JokerCardHandTest {
    @TestFactory
    fun `should parse the card hand correctly`() = listOf(
        "23456 101" to Pair(HIGH_CARD, 101),
        "AA234 102" to Pair(ONE_PAIR, 102),
        "J2345 103" to Pair(ONE_PAIR, 103),
        "KKQQ2 104" to Pair(TWO_PAIR, 104),
        "TTT23 105" to Pair(THREE_KIND, 105),
        "TTJ23 106" to Pair(THREE_KIND, 106),
        "TJJ23 107" to Pair(THREE_KIND, 107),
        "44555 108" to Pair(FULL_HOUSE, 108),
        "4455J 109" to Pair(FULL_HOUSE, 109),
        "66667 110" to Pair(FOUR_KIND, 110),
        "666J7 111" to Pair(FOUR_KIND, 111),
        "66JJ7 112" to Pair(FOUR_KIND, 112),
        "6JJJ7 113" to Pair(FOUR_KIND, 113),
        "88888 114" to Pair(FIVE_KIND, 114),
        "8888J 115" to Pair(FIVE_KIND, 115),
        "888JJ 116" to Pair(FIVE_KIND, 116),
        "88JJJ 117" to Pair(FIVE_KIND, 117),
        "8JJJJ 118" to Pair(FIVE_KIND, 118),
        "JJJJJ 119" to Pair(FIVE_KIND, 119),
    ).map { (line, expected) ->
        val (type, bid) = expected
        dynamicTest("should parse the card hand correctly [$line] -> $type") {
            val hand = JokerCardHand.parse(line)

            assertEquals(bid, hand.bid)
            assertEquals(type, hand.type)
        }
    }
}
