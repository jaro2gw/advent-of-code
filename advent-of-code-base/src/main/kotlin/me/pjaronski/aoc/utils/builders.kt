package me.pjaronski.aoc.utils

fun StringBuilder.flush(): String = toString().also { clear() }

fun StringBuilder.number(): Int? = flush().toIntOrNull()
