package me.pjaronski.aoc.v22.day05

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v22.day05.crate.mover.CrateMover
import me.pjaronski.aoc.v22.day05.crate.mover.CrateMover9000
import me.pjaronski.aoc.v22.day05.crate.mover.CrateMover9001
import me.pjaronski.aoc.v22.day05.crate.mover.CrateMoverOperation
import kotlin.math.max

fun main() = present(Solution05)

object Solution05 : Solution<String, String> {
    private val OPERATION_REGEX = Regex("move (\\d+) from (\\d+) to (\\d+)")
    private val CRATE_REGEX = Regex("\\[(.)]")

    private fun split(
        input: Input,
        supplyCrateMover: (Int) -> CrateMover
    ): Pair<CrateMover, List<CrateMoverOperation>> {
        var size = 0
        val crates = mutableListOf<String>()
        val operations = mutableListOf<CrateMoverOperation>()

        var afterBlank = false
        input.lines().forEach { line ->
            if (line.isBlank()) afterBlank = true
            else if (afterBlank) operations += operation(line)
            else {
                val estimation = (line.length + 1) / 4
                size = max(size, estimation)
                crates += line
            }
        }

        val mover = supplyCrateMover(size)
        crates.reversed()
            .drop(1) // labels
            .forEach { line ->
                CRATE_REGEX.findAll(line)
                    .forEach { result ->
                        val index = result.range.first / 4
                        val crate = result.groupValues[1][0]
                        mover.push(index, crate)
                    }
            }

        return mover to operations
    }

    private fun operation(line: String): CrateMoverOperation {
        val (count, source, target) = OPERATION_REGEX.find(line)!!.groupValues.drop(1).map { it.toInt() }
        return CrateMoverOperation(count, source - 1, target - 1)
    }

    override fun part1(input: Input): String {
        val (mover, operations) = split(input, ::CrateMover9000)
        operations.forEach { mover.perform(it) }
        return mover.peek()
    }

    override fun part2(input: Input): String {
        val (mover, operations) = split(input, ::CrateMover9001)
        operations.forEach { mover.perform(it) }
        return mover.peek()
    }
}
