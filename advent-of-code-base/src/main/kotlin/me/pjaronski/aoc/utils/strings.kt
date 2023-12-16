package me.pjaronski.aoc.utils

import me.pjaronski.aoc.Presenter.newline

fun <T> Array<Array<T>>.toString2D(
    transform: (T) -> Char
): String = joinToString(separator = newline) { values ->
    values.joinToString(separator = "") { transform(it).toString() }
}

fun Array<BooleanArray>.toString2D(
    transform: (Boolean) -> Char
): String = joinToString(separator = newline) { values ->
    values.joinToString(separator = "") { transform(it).toString() }
}

fun <C : Iterable<T>, T> Iterable<C>.toString2D(
    transform: (T) -> Char
): String = joinToString(separator = newline) { values ->
    values.joinToString(separator = "") { transform(it).toString() }
}

fun <C : Iterable<Char>> Iterable<C>.toString2D(): String = joinToString(separator = newline) { values ->
    values.joinToString(separator = "")
}

fun List<String>.alignEnd(padChar: Char): List<String> {
    val length = maxOfOrNull { it.length } ?: 0
    return map { it.padEnd(length, padChar) }
}

fun List<String>.padAround(padChar: Char): List<String> {
    val lines = alignEnd(padChar)
    val length = lines.firstOrNull()?.length ?: 0
    val line = String(CharArray(length + 2) { padChar })
    return listOf(line) + lines.map { padChar + it + padChar } + listOf(line)
}
