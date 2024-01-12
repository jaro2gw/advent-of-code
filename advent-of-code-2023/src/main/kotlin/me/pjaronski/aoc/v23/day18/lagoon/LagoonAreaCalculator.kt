package me.pjaronski.aoc.v23.day18.lagoon

import me.pjaronski.aoc.utils.Coords
import kotlin.math.abs
import kotlin.math.absoluteValue

object LagoonAreaCalculator {
    fun calculateArea(coords: List<Coords>): Long {
        val chain = coords.plus(coords[0]).map { (row, col) -> row.toLong() to col.toLong() }

        val circumference = chain.zipWithNext { (r1, c1), (r2, c2) -> abs(r2 - r1) + abs(c2 - c1) }
            .sum()
            .div(2)

        val area = chain.zipWithNext { (r1, c1), (r2, c2) -> r1 * c2 - c1 * r2 }
            .sum()
            .div(2)
            .absoluteValue

        return area + circumference + 1
    }
}
