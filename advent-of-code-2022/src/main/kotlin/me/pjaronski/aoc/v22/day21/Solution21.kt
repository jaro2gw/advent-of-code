package me.pjaronski.aoc.v22.day21

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.NUMBER_PATTERN
import me.pjaronski.aoc.v22.day21.monkey.Monkey
import me.pjaronski.aoc.v22.day21.monkey.MonkeyWithNumber
import me.pjaronski.aoc.v22.day21.monkey.MonkeyWithOperation
import me.pjaronski.aoc.v22.day21.operation.Operation

fun main() = present(Solution21)

private typealias Numbers = Map<String, Long>
private typealias Operations = Map<String, Triple<String, Char, String>>

object Solution21 : Solution<Long, Long> {
    private const val MONKEY_NAME_PATTERN = "([a-z]{4})"
    private val MONKEY_WITH_NUMBER_REGEX = Regex(
        "$MONKEY_NAME_PATTERN: $NUMBER_PATTERN"
    )
    private val MONKEY_WITH_OPERATION_REGEX = Regex(
        "$MONKEY_NAME_PATTERN: $MONKEY_NAME_PATTERN ([-+*/]) $MONKEY_NAME_PATTERN"
    )

    private fun monkeys(input: Input): Pair<Numbers, Operations> {
        val numbers = mutableMapOf<String, Long>()
        val operations = mutableMapOf<String, Triple<String, Char, String>>()

        input.lines().forEach { line ->
            val monkeyWithNumber = MONKEY_WITH_NUMBER_REGEX.find(line)
            if (monkeyWithNumber != null) {
                val name = monkeyWithNumber.groupValues[1]
                val number = monkeyWithNumber.groupValues[2].toLong()

                numbers[name] = number
            } else {
                val monkeyWithOperation = MONKEY_WITH_OPERATION_REGEX.find(line)!!

                val name = monkeyWithOperation.groupValues[1]
                val name1 = monkeyWithOperation.groupValues[2]
                val char = monkeyWithOperation.groupValues[3].single()
                val name2 = monkeyWithOperation.groupValues[4]

                operations[name] = Triple(name1, char, name2)
            }
        }

        return numbers to operations
    }

    private fun monkey(name: String, numbers: Numbers, operations: Operations): Monkey {
        val number = numbers[name]
        return if (number != null) MonkeyWithNumber(name, number)
        else {
            val (name1, char, name2) = operations[name]!!
            val monkey1 = monkey(name1, numbers, operations)
            val monkey2 = monkey(name2, numbers, operations)
            val operation = Operation.fromChar(char)
            MonkeyWithOperation(name, monkey1, monkey2, operation)
        }
    }

    override fun part1(input: Input): Long {
        val (numbers, operations) = monkeys(input)
        val root = monkey("root", numbers, operations)
        return root.yell()
    }

    override fun part2(input: Input): Long {
        val (numbers, operations) = monkeys(input)
        val root = monkey("root", numbers, operations) as MonkeyWithOperation
        val santa = root.find("humn")!!
        return root.figureOutWhatMonkeyShouldYell(santa)
    }
}
