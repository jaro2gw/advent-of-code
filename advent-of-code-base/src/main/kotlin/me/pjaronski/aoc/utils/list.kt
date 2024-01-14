package me.pjaronski.aoc.utils

fun <T> Iterable<T>.head(): Pair<T?, List<T>> = firstOrNull() to drop(1)

fun <T> Iterable<T>.tail(): Pair<List<T>, T?> = toList().dropLast(1) to lastOrNull()
