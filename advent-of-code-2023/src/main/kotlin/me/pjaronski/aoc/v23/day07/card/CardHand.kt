package me.pjaronski.aoc.v23.day07.card

sealed class CardHand {
    abstract val cards: List<Int>
    abstract val bid: Int
    abstract val type: CardHandType

    protected fun yikes(): Nothing = throw IllegalStateException("Card hand cannot have more than 5 cards")
}
