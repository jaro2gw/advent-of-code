package me.pjaronski.aoc.v23.day07.card

object CardHandComparator : Comparator<CardHand> {
    override fun compare(hand1: CardHand, hand2: CardHand): Int {
        return if (hand1.type != hand2.type) hand1.type.ordinal - hand2.type.ordinal
        else hand1.cards.zip(hand2.cards)
            .map { (card1, card2) -> card1 - card2 }
            .find { it != 0 }
            ?: 0
    }
}
