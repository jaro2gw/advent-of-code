package me.pjaronski.aoc.v23.day19.parts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertNotEquals

class PartSorterTest {
    @Test
    fun `should create empty range when lower greater that upper`() {
        val actual = PartSorter.Range.of(1000, 0)
        val expected = PartSorter.Range.EMPTY
        assertEquals(expected, actual)
    }

    @Test
    fun `should not create empty range when lower equal to upper`() {
        val actual = PartSorter.Range.of(1000, 1000)
        val illegal = PartSorter.Range.EMPTY
        assertNotEquals(illegal, actual)
    }
}
