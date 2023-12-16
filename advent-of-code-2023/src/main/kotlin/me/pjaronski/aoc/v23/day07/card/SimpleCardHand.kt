package me.pjaronski.aoc.v23.day07.card

import me.pjaronski.aoc.v23.day07.card.CardHandType.FIVE_KIND
import me.pjaronski.aoc.v23.day07.card.CardHandType.FOUR_KIND
import me.pjaronski.aoc.v23.day07.card.CardHandType.FULL_HOUSE
import me.pjaronski.aoc.v23.day07.card.CardHandType.HIGH_CARD
import me.pjaronski.aoc.v23.day07.card.CardHandType.ONE_PAIR
import me.pjaronski.aoc.v23.day07.card.CardHandType.THREE_KIND
import me.pjaronski.aoc.v23.day07.card.CardHandType.TWO_PAIR

data class SimpleCardHand(
    override val cards: List<Int>,
    override val bid: Int,
) : CardHand() {
    override val type: CardHandType

    init {
        val counts = cards.groupingBy { it }.eachCount()
        type = when (counts.size) {
            1 -> FIVE_KIND
            2 -> if (counts.containsValue(4)) FOUR_KIND else FULL_HOUSE
            3 -> if (counts.containsValue(3)) THREE_KIND else TWO_PAIR
            4 -> ONE_PAIR
            5 -> HIGH_CARD
            else -> yikes()
        }
    }

    companion object {
        private const val CARDS = "23456789TJQKA"

        fun parse(line: String): CardHand {
            val (cardsPart, bidPart) = line.split(' ')

            val cards = cardsPart.map { CARDS.indexOf(it) }
            val bid = bidPart.toInt()

            return SimpleCardHand(cards, bid)
        }
    }
}
