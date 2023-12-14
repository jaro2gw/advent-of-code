package me.pjaronski.aoc.v23.day07.card

import me.pjaronski.aoc.v23.day07.card.CardHandType.*

data class JokerCardHand(
    override val cards: List<Int>,
    override val bid: Int,
) : CardHand() {
    override val type: CardHandType

    init {
        val counts = cards.groupingBy { it }.eachCount()

        val currentBestType = when (counts.size) {
            1 -> FIVE_KIND
            2 -> if (counts.containsValue(4)) FOUR_KIND else FULL_HOUSE
            3 -> if (counts.containsValue(3)) THREE_KIND else TWO_PAIR
            4 -> ONE_PAIR
            5 -> HIGH_CARD
            else -> yikes()
        }

        type = when (counts[JOKER] ?: 0) {
            0 -> currentBestType
            1 -> when(currentBestType) {
                FOUR_KIND -> FIVE_KIND
                THREE_KIND -> FOUR_KIND
                TWO_PAIR -> FULL_HOUSE
                ONE_PAIR -> THREE_KIND
                HIGH_CARD -> ONE_PAIR
                else -> yikes()
            }
            2 -> when(currentBestType) {
                FULL_HOUSE -> FIVE_KIND
                TWO_PAIR -> FOUR_KIND
                ONE_PAIR -> THREE_KIND
                else -> yikes()
            }
            3 -> when(currentBestType) {
                FULL_HOUSE -> FIVE_KIND
                THREE_KIND -> FOUR_KIND
                else -> yikes()
            }
            4 -> FIVE_KIND
            5 -> FIVE_KIND
            else -> yikes()
        }
    }

    companion object {
        private const val CARDS = "J23456789TQKA"
        private const val JOKER = 0

        fun parse(line: String): CardHand {
            val (cardsPart, bidPart) = line.split(' ')

            val cards = cardsPart.map { CARDS.indexOf(it) }
            val bid = bidPart.toInt()

            return JokerCardHand(cards, bid)
        }
    }
}
