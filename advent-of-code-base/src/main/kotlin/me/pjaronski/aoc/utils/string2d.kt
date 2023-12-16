package me.pjaronski.aoc.utils

import me.pjaronski.aoc.Presenter.newline

fun <T> Array<Array<T>>.toString2D(
    transform: ((T) -> CharSequence)? = null
): String = joinToString(separator = newline) {
    it.joinToString(separator = "", transform = transform)
}

fun Array<BooleanArray>.toString2D(
    transform: ((Boolean) -> CharSequence)? = null
): String = joinToString(separator = newline) {
    it.joinToString(separator = "", transform = transform)
}

fun <C, T> Iterable<C>.toString2D(
    transform: ((T) -> CharSequence)? = null
): String where C : Iterable<T> = joinToString(separator = newline) {
    it.joinToString(separator = "", transform = transform)
}

fun Iterable<BooleanArray>.toString2D(
    transform: ((Boolean) -> CharSequence)? = null
) = joinToString(separator = newline) {
    it.joinToString(separator = "", transform = transform)
}
