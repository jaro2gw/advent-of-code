package me.pjaronski.aoc.v23.day15.lens

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LensHashTest {
    @Test
    fun `should calculate hash correctly`() {
        val actual = LensHash.of("HASH")
        val expected = 52

        assertEquals(expected, actual)
    }
}
