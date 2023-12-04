package me.pjaronski.aoc.v23.day01.number

internal sealed class NumberCharacterChain {
    companion object {
        fun from(wrapper: NumberWrapper): NumberCharacterNode {
            check(wrapper.string.isNotEmpty())
            val chain = wrapper.string.foldRight<NumberCharacterChain>(
                initial = NumberFound(wrapper.number)
            ) { digit, chain ->
                NumberCharacterNode(digit, chain)
            }
            return chain as NumberCharacterNode
        }
    }
}

internal data class NumberCharacterNode(
    val digit: Char,
    val next: NumberCharacterChain
) : NumberCharacterChain()

internal data class NumberFound(
    val number: Int
) : NumberCharacterChain()
