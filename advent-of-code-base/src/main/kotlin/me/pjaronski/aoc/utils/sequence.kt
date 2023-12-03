package me.pjaronski.aoc.utils

fun <T> infinite(elements: Iterable<T>): Sequence<T> = sequence {
    while (true) {
        yieldAll(elements)
    }
}
