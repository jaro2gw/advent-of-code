package me.pjaronski.aoc.v23.day01.number

class NumberWrapper internal constructor(
    val number: Int,
    val string: String
) {
    companion object {
        val WRAPPERS_NUM = listOf(
            NumberWrapper(0),
            NumberWrapper(1),
            NumberWrapper(2),
            NumberWrapper(3),
            NumberWrapper(4),
            NumberWrapper(5),
            NumberWrapper(6),
            NumberWrapper(7),
            NumberWrapper(8),
            NumberWrapper(9),
        )
        val WRAPPERS_LEX = listOf(
            NumberWrapper(0, "zero"),
            NumberWrapper(1, "one"),
            NumberWrapper(2, "two"),
            NumberWrapper(3, "three"),
            NumberWrapper(4, "four"),
            NumberWrapper(5, "five"),
            NumberWrapper(6, "six"),
            NumberWrapper(7, "seven"),
            NumberWrapper(8, "eight"),
            NumberWrapper(9, "nine"),
        )
        val WRAPPERS_ALL = WRAPPERS_NUM + WRAPPERS_LEX
    }

    internal constructor(number: Int) : this(number, number.toString())

    operator fun component1() = number
    operator fun component2() = string
}
