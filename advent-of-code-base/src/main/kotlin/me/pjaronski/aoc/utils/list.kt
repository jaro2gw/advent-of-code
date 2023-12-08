package me.pjaronski.aoc.utils

fun <T> List<T>.head(): Pair<T?, List<T>> = firstOrNull() to drop(1)
