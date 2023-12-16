package me.pjaronski.aoc.v23.day05.garden

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GardenResourceMappingTest {
    @Test
    fun `should map source value correctly`() {
        val mapping = GardenResourceMapping(
            rules = listOf(
                // target, source, range
                Triple(0, 10, 20),
                Triple(20, 0, 10),
            )
        )

        val source1 = 0L
        val source2 = 5L
        val source3 = 10L
        val source4 = 15L
        val source5 = 20L
        val source6 = 25L
        val source7 = 30L

        val target1 = mapping.map(source1)
        val target2 = mapping.map(source2)
        val target3 = mapping.map(source3)
        val target4 = mapping.map(source4)
        val target5 = mapping.map(source5)
        val target6 = mapping.map(source6)
        val target7 = mapping.map(source7)

        assertEquals(20, target1)
        assertEquals(25, target2)
        assertEquals(0, target3)
        assertEquals(5, target4)
        assertEquals(10, target5)
        assertEquals(15, target6)
        assertEquals(30, target7)
    }

    @Test
    fun `should split values correctly when no rules overlap`() {
        val mapping = GardenResourceMapping(
            rules = listOf(
                // target, source, range
                Triple(0, 10, 20),
                Triple(20, 0, 10),
            )
        )

        val values1 = 50L until 100L
        val values2 = -100L until -50L

        val splits1 = mapping.split(values1)
        val splits2 = mapping.split(values2)

        assertEquals(listOf(50L until 100L), splits1)
        assertEquals(listOf(-100L until -50L), splits2)
    }

    @Test
    fun `should split values correctly when partial overlap with one rule`() {
        val mapping = GardenResourceMapping(
            rules = listOf(
                // target, source, range
                Triple(0, 10, 20),
                Triple(20, 0, 10),
            )
        )

        val values1 = -5L until 5L
        val values2 = 25L until 35L

        val splits1 = mapping.split(values1)
        val splits2 = mapping.split(values2)

        assertEquals(listOf(-5L until 0L, 0L until 5L), splits1)
        assertEquals(listOf(25L until 30L, 30L until 35L), splits2)
    }

    @Test
    fun `should split values correctly when overlap across multiple rules`() {
        val mapping = GardenResourceMapping(
            rules = listOf(
                // target, source, range
                Triple(0, 10, 20),
                Triple(20, 0, 10),
            )
        )

        val values1 = -5L until 35L
        val values2 = 5L until 25L

        val splits1 = mapping.split(values1)
        val splits2 = mapping.split(values2)

        assertEquals(listOf(-5L until 0L, 0L until 10L, 10L until 30L, 30L until 35L), splits1)
        assertEquals(listOf(5L until 10L, 10L until 25L), splits2)
    }

    @Test
    fun `should map values correctly when no rules overlap`() {
        val mapping = GardenResourceMapping(
            rules = listOf(
                // target, source, range
                Triple(0, 10, 20),
                Triple(20, 0, 10),
            )
        )

        val values1 = 50L until 100L
        val values2 = -100L until -50L

        val splits1 = mapping.map(values1)
        val splits2 = mapping.map(values2)

        assertEquals(listOf(50L until 100L), splits1)
        assertEquals(listOf(-100L until -50L), splits2)
    }

    @Test
    fun `should map values correctly when partial overlap with one rule`() {
        val mapping = GardenResourceMapping(
            rules = listOf(
                // target, source, range
                Triple(0, 10, 20),
                Triple(20, 0, 10),
            )
        )

        val values1 = -5L until 5L
        val values2 = 25L until 35L

        val splits1 = mapping.map(values1)
        val splits2 = mapping.map(values2)

        assertEquals(listOf(-5L until 0L, 20L until 25L), splits1)
        assertEquals(listOf(15L until 20L, 30L until 35L), splits2)
    }

    @Test
    fun `should map values correctly when overlap across multiple rules`() {
        val mapping = GardenResourceMapping(
            rules = listOf(
                // target, source, range
                Triple(0, 10, 20),
                Triple(20, 0, 10),
            )
        )

        val values1 = -5L until 35L
        val values2 = 5L until 25L

        val splits1 = mapping.map(values1)
        val splits2 = mapping.map(values2)

        assertEquals(listOf(-5L until 0L, 20L until 30L, 0L until 20L, 30L until 35L), splits1)
        assertEquals(listOf(25L until 30L, 0L until 15L), splits2)
    }
}
