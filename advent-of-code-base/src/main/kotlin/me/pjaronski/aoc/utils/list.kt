package me.pjaronski.aoc.utils

fun <T> Iterable<T>.head(): Pair<T?, List<T>> = firstOrNull() to drop(1)
fun CharSequence.head(): Pair<Char?, CharSequence> = firstOrNull() to drop(1)
