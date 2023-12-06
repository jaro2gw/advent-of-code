package me.pjaronski.aoc

import kotlin.time.measureTimedValue

object Presenter {
    val newline: String = System.lineSeparator()

    private fun <P> present(part: Int, code: () -> P) {
        val (p, duration) = measureTimedValue(code)
        val answer = p?.toString()
        if (answer != null) {
            println("Part $part:")
            val prefix = if (answer.contains(newline)) newline else ""
            val string = prefix + answer
            println("\tSolution: $string")
            println("\tDuration: $duration")
        }
    }

    fun <P1, P2> present(solution: Solution<P1, P2>) {
        val input = Input(solution::class.java, file = "input.txt")
        present(solution, input)
    }

    fun <P1, P2> present(solution: Solution<P1, P2>, input: Input) {
        present(part = 1) { solution.part1(input) }
        present(part = 2) { solution.part2(input) }
    }
}
