package me.pjaronski.aoc.v23.day09.oasis

object OasisAndSandInstabilitySensor {
    fun predict(values: List<Long>): Long =
        if (values.all { it == 0L }) 0L
        else {
            val diff = values.zipWithNext { v1, v2 -> v2 - v1 }
            predict(diff) + values.last()
        }
}
