package me.pjaronski.aoc.utils

/** returns an infinitely repeating sequence of the provided [elements] */
fun <T> infinite(elements: Iterable<T>): Sequence<T> = sequence {
    while (true) {
        yieldAll(elements)
    }
}

/** returns an infinitely repeating sequence of the provided [elements] with an index of each element within an original iterable */
fun <T> infiniteWithElementIndex(elements: Iterable<T>): Sequence<IndexedValue<T>> = sequence {
    while (true) {
        yieldAll(elements = elements.withIndex())
    }
}
