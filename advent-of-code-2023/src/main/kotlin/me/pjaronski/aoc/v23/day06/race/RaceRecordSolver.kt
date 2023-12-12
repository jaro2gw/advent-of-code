package me.pjaronski.aoc.v23.day06.race

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

object RaceRecordSolver {
    /** specifies the bounds of how long the button can be held down in order to beat the race record */
    fun solve(race: Race): Pair<Long, Long> {
        val a = -1
        val b = race.time
        val c = -race.distance

        val delta = sqrt(b * b - 4.0 * a * c)

        val t0 = floor((-b + delta) / -2 + 1).toLong()
        val t1 = ceil((-b - delta) / -2 - 1).toLong()

        return t0 to t1
    }
}
