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
