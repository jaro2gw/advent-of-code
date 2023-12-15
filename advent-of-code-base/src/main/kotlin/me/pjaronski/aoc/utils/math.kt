package me.pjaronski.aoc.utils

tailrec fun gcd(num1: Long, num2: Long): Long =
    if (num2 == 0L) num1
    else gcd(num2, num1 % num2)

fun lcm(num1: Long, num2: Long): Long = (num1 * num2) / gcd(num1, num2)

tailrec fun gcd(num1: Int, num2: Int): Int =
    if (num2 == 0) num1
    else gcd(num2, num1 % num2)

fun lcm(num1: Int, num2: Int): Int = (num1 * num2) / gcd(num1, num2)
