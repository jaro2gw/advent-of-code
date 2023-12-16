package me.pjaronski.aoc.v23.day09.oasis

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OasisAndSandInstabilitySensorTest {
    @Test
    fun `should predict next value if given only zeroes`() {
        val values = listOf<Long>(0, 0, 0, 0)
        val prediction = OasisAndSandInstabilitySensor.predict(values)
        assertEquals(0, prediction)
    }

    @Test
    fun `should predict next value if given a single repeating number`() {
        val values = listOf<Long>(3, 3, 3, 3)
        val prediction = OasisAndSandInstabilitySensor.predict(values)
        assertEquals(3, prediction)
    }

    @Test
    fun `should predict next value if given increasing numbers with a constant difference #1`() {
        val values = listOf<Long>(1, 2, 3, 4)
        val prediction = OasisAndSandInstabilitySensor.predict(values)
        assertEquals(5, prediction)
    }

    @Test
    fun `should predict next value if given increasing numbers with a constant difference #2`() {
        val values = listOf<Long>(0, 2, 4, 6)
        val prediction = OasisAndSandInstabilitySensor.predict(values)
        assertEquals(8, prediction)
    }



    @Test
    fun `should predict next value if given increasing numbers with an increasing difference #1`() {
        val values = listOf<Long>(0, 1, 3, 6)
        val prediction = OasisAndSandInstabilitySensor.predict(values)
        assertEquals(10, prediction)
    }
}
