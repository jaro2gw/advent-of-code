package me.pjaronski.aoc.v22.day10.cpu

import java.lang.System.lineSeparator

class CRT {
    private val board = Array(6) {
        BooleanArray(40)
    }

    operator fun set(row: Int, col: Int, lit: Boolean) {
        board[row][col] = lit
    }

    override fun toString(): String = board.joinToString(separator = lineSeparator()) { pixels ->
        pixels.joinToString(separator = "") { lit ->
            if (lit) "#"
            else "."
        }
    }
}
