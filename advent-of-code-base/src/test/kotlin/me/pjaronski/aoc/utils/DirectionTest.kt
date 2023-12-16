package me.pjaronski.aoc.utils

import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DirectionTest {
    @Test
    fun `direction should have correct opposite`() {
        assertEquals(SOUTH, NORTH.opposite)
        assertEquals(WEST, EAST.opposite)
        assertEquals(NORTH, SOUTH.opposite)
        assertEquals(EAST, WEST.opposite)
    }
}
