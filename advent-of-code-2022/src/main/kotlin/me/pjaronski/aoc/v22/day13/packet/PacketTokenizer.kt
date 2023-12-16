package me.pjaronski.aoc.v22.day13.packet

import me.pjaronski.aoc.utils.number
import me.pjaronski.aoc.v22.day13.packet.PacketTokenizer.Token.BRACKET_L
import me.pjaronski.aoc.v22.day13.packet.PacketTokenizer.Token.BRACKET_R
import me.pjaronski.aoc.v22.day13.packet.PacketTokenizer.Token.NUMBER

class PacketTokenizer {
    sealed class Token {
        data object BRACKET_L : Token()
        data object BRACKET_R : Token()
        data class NUMBER(val number: Int) : Token()
    }

    private fun number(builder: StringBuilder): NUMBER? = builder.number()?.let { NUMBER(it) }

    fun tokenize(string: String): Sequence<Token> = sequence {
        val builder = StringBuilder()

        string.forEach { char ->
            when (char) {
                '[' -> yield(BRACKET_L)
                ']' -> {
                    val num = number(builder)
                    if (num != null) yield(num)
                    yield(BRACKET_R)
                }

                ',' -> {
                    val num = number(builder)
                    if (num != null) yield(num)
                }

                else -> builder.append(char)
            }
        }
    }
}
