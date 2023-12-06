package me.pjaronski.aoc.v22.day10.cpu

import me.pjaronski.aoc.Presenter.newline

class CRT {
    private val board = Array(6) {
        BooleanArray(40)
    }

    operator fun set(row: Int, col: Int, lit: Boolean) {
        board[row][col] = lit
    }

    override fun toString(): String = board.joinToString(separator = newline) { pixels ->
        pixels.joinToString(separator = "") { lit ->
            if (lit) "#"
            else "."
        }
    }
}
