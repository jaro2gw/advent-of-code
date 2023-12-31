package me.pjaronski.aoc.v22.day10

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v22.day10.command.Command
import me.pjaronski.aoc.v22.day10.cpu.CPU
import me.pjaronski.aoc.v22.day10.cpu.CRT

fun main() = present(Solution10)

object Solution10 : Solution<Int, String> {
    override fun part1(input: Input): Int {
        var signal = 0

        val cpu = CPU { (clock, register) ->
            when (clock) {
                20, 60, 100, 140, 180, 220 -> signal += clock * register
            }
        }

        input.lines()
            .map { Command.fromString(it) }
            .forEach { cpu.execute(it) }

        return signal
    }

    override fun part2(input: Input): String {
        val crt = CRT()
        val cpu = CPU { (clock, sprite) ->
            val clk = clock - 1

            val row = clk / 40
            val col = clk % 40
            val lit = sprite - 1 <= col && col <= sprite + 1

            crt[row, col] = lit
        }

        input.lines()
            .map { Command.fromString(it) }
            .forEach { cpu.execute(it) }

        return crt.toString()
    }
}
