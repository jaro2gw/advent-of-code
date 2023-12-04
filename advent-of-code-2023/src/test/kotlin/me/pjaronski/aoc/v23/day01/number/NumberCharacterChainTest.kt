package me.pjaronski.aoc.v23.day01.number

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class NumberCharacterChainTest {
    private fun node(digit: Char, next: NumberCharacterChain) = NumberCharacterNode(digit, next)
    private fun node(digit: Char, number: Int) = node(digit, NumberFound(number))

    @Test
    fun `should convert num wrappers`() {
        val actual = NumberWrapper.WRAPPERS_NUM.map { NumberCharacterChain.from(it) }
        val expected = listOf(
            node('0', 0),
            node('1', 1),
            node('2', 2),
            node('3', 3),
            node('4', 4),
            node('5', 5),
            node('6', 6),
            node('7', 7),
            node('8', 8),
            node('9', 9),
        )

        assertContentEquals(expected, actual)
    }

    @Test
    fun `should convert lex wrappers`() {
        val actual = NumberWrapper.WRAPPERS_LEX.map { NumberCharacterChain.from(it) }
        val expected = listOf(
            node('z', node('e', node('r', node('o', 0)))),
            node('o', node('n', node('e', 1))),
            node('t', node('w', node('o', 2))),
            node('t', node('h', node('r', node('e', node('e', 3))))),
            node('f', node('o', node('u', node('r', 4)))),
            node('f', node('i', node('v', node('e', 5)))),
            node('s', node('i', node('x', 6))),
            node('s', node('e', node('v', node('e', node('n', 7))))),
            node('e', node('i', node('g', node('h', node('t', 8))))),
            node('n', node('i', node('n', node('e', 9)))),
        )

        assertContentEquals(expected, actual)
    }
}