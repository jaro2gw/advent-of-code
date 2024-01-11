package me.pjaronski.aoc.v23.day17.heat

import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST
import me.pjaronski.aoc.v23.day17.heat.HeatMapNavigator.PathExtension
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class HeatMapNavigatorTest {
    @Test
    fun `should calculate crucible extensions correctly #0`() {
        val extension = PathExtension(coords = Coords(row = 0, 0), direction = EAST, streak = 0)

        val actual = HeatMapNavigator.crucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(0, 1), NORTH, 0),
            PathExtension(Coords(0, 1), SOUTH, 0),
            PathExtension(Coords(0, 1), EAST, 1),
        )
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should calculate crucible extensions correctly #1`() {
        val extension = PathExtension(coords = Coords(row = 0, col = 1), direction = EAST, streak = 1)

        val actual = HeatMapNavigator.crucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(0, 2), NORTH, 0),
            PathExtension(Coords(0, 2), SOUTH, 0),
            PathExtension(Coords(0, 2), EAST, 2),
        )
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should calculate crucible extensions correctly #2`() {
        val extension = PathExtension(coords = Coords(row = 0, col = 2), direction = EAST, streak = 2)

        val actual = HeatMapNavigator.crucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(0, 3), NORTH, 0),
            PathExtension(Coords(0, 3), SOUTH, 0),
        )
        assertContentEquals(expected, actual)
    }


    @Test
    fun `should calculate ultra crucible extensions correctly #0`() {
        val extension = PathExtension(coords = Coords(row = 0, col = 0), direction = SOUTH, streak = 0)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(1, 0), SOUTH, 1),
        )
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should calculate ultra crucible extensions correctly #1`() {
        val extension = PathExtension(coords = Coords(row = 1, col = 0), direction = SOUTH, streak = 1)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(2, 0), SOUTH, 2),
        )
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should calculate ultra crucible extensions correctly #2`() {
        val extension = PathExtension(coords = Coords(row = 2, col = 0), direction = SOUTH, streak = 2)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(3, 0), SOUTH, 3),
        )
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should calculate ultra crucible extensions correctly #3`() {
        val extension = PathExtension(coords = Coords(row = 3, col = 0), direction = SOUTH, streak = 3)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(4, 0), EAST, 0),
            PathExtension(Coords(4, 0), WEST, 0),
            PathExtension(Coords(4, 0), SOUTH, 4),
        )
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should calculate ultra crucible extensions correctly #4`() {
        val extension = PathExtension(coords = Coords(row = 4, col = 0), direction = SOUTH, streak = 4)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(5, 0), EAST, 0),
            PathExtension(Coords(5, 0), WEST, 0),
            PathExtension(Coords(5, 0), SOUTH, 5),
        )
        assertContentEquals(expected, actual)
    }
    @Test
    fun `should calculate ultra crucible extensions correctly #5`() {
        val extension = PathExtension(coords = Coords(row = 5, col = 0), direction = SOUTH, streak = 5)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(6, 0), EAST, 0),
            PathExtension(Coords(6, 0), WEST, 0),
            PathExtension(Coords(6, 0), SOUTH, 6),
        )
        assertContentEquals(expected, actual)
    }
    @Test
    fun `should calculate ultra crucible extensions correctly #6`() {
        val extension = PathExtension(coords = Coords(row = 6, col = 0), direction = SOUTH, streak = 6)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(7, 0), EAST, 0),
            PathExtension(Coords(7, 0), WEST, 0),
            PathExtension(Coords(7, 0), SOUTH, 7),
        )
        assertContentEquals(expected, actual)
    }
    @Test
    fun `should calculate ultra crucible extensions correctly #7`() {
        val extension = PathExtension(coords = Coords(row = 7, col = 0), direction = SOUTH, streak = 7)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(8, 0), EAST, 0),
            PathExtension(Coords(8, 0), WEST, 0),
            PathExtension(Coords(8, 0), SOUTH, 8),
        )
        assertContentEquals(expected, actual)
    }
    @Test
    fun `should calculate ultra crucible extensions correctly #8`() {
        val extension = PathExtension(coords = Coords(row = 8, col = 0), direction = SOUTH, streak = 8)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(9, 0), EAST, 0),
            PathExtension(Coords(9, 0), WEST, 0),
            PathExtension(Coords(9, 0), SOUTH, 9),
        )
        assertContentEquals(expected, actual)
    }
    @Test
    fun `should calculate ultra crucible extensions correctly #9`() {
        val extension = PathExtension(coords = Coords(row = 9, col = 0), direction = SOUTH, streak = 9)

        val actual = HeatMapNavigator.ultraCrucibleExtensions(extension)
        val expected = listOf(
            PathExtension(Coords(10, 0), EAST, 0),
            PathExtension(Coords(10, 0), WEST, 0),
        )
        assertContentEquals(expected, actual)
    }
}
