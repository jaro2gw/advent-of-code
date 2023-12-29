package me.pjaronski.aoc.utils

fun Array<CharArray>.deepCopy() = Array(size) { this[it].copyOf() }
