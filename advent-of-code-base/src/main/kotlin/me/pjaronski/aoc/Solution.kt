package me.pjaronski.aoc

interface Solution<P1, P2> {
    fun part1(input: Input): P1
    fun part2(input: Input): P2
}
