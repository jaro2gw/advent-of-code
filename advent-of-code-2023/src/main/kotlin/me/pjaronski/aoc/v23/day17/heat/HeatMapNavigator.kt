package me.pjaronski.aoc.v23.day17.heat

import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.Coords.Companion.ORIGIN
import me.pjaronski.aoc.utils.Coords.Companion.manhattanDistance
import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Turn.LEFT
import me.pjaronski.aoc.utils.Turn.RIGHT
import me.pjaronski.aoc.utils.contains
import me.pjaronski.aoc.utils.get
import java.util.PriorityQueue
import kotlin.Int.Companion.MAX_VALUE
import kotlin.collections.set

object HeatMapNavigator {
    internal data class PathExtension(
        val coords: Coords,
        val direction: Direction,
        val streak: Int = 0,
    )

    private fun extensions(extension: PathExtension, min: Int, max: Int): List<PathExtension> {
        val (coords, direction, streak) = extension
        val next = coords + direction
        val extensions = mutableListOf<PathExtension>()

        if (streak >= min) {
            extensions += PathExtension(next, direction = LEFT(direction))
            extensions += PathExtension(next, direction = RIGHT(direction))
        }
        if (streak < max) extensions += PathExtension(next, direction, streak + 1)

        return extensions
    }

    internal fun crucibleExtensions(extension: PathExtension): List<PathExtension> = extensions(extension, 0, 2)

    internal fun ultraCrucibleExtensions(extension: PathExtension): List<PathExtension> = extensions(extension, 3, 9)

    fun minHeatLoss(loss: Array<IntArray>, ultra: Boolean = false): Int =
        if (ultra) minHeatLoss(loss, ::ultraCrucibleExtensions)
        else minHeatLoss(loss, ::crucibleExtensions)

    private fun minHeatLoss(
        loss: Array<IntArray>,
        extensions: (PathExtension) -> List<PathExtension>,
    ): Int {
        val last = Coords(
            row = loss.lastIndex,
            col = loss.last().lastIndex
        )

        val start1 = PathExtension(ORIGIN, EAST)
        val start2 = PathExtension(ORIGIN, SOUTH)

        val gScore = mutableMapOf<PathExtension, Int>()
        gScore[start1] = 0
        gScore[start2] = 0
        fun gScore(extension: PathExtension) = gScore[extension] ?: MAX_VALUE

        val fScore = mutableMapOf<PathExtension, Int>()
        fScore[start1] = last.row + last.col
        fScore[start2] = last.row + last.col
        fun fScore(extension: PathExtension) = fScore[extension] ?: MAX_VALUE

        val openSet = PriorityQueue<PathExtension> { ext1, ext2 ->
            fScore(ext1) compareTo fScore(ext2)
        }

        openSet += start1
        openSet += start2

        while (openSet.isNotEmpty()) {
            val extension = openSet.poll()
            if (extension.coords == last) return gScore(extension)

            extensions(extension)
                .filter { (coords, direction, _) -> (coords + direction) in loss }
                .forEach { ext ->
                    val (coords, _, _) = ext
                    val tScore = gScore(extension) + loss[coords]
                    if (tScore < gScore(ext)) {
                        gScore[ext] = tScore
                        fScore[ext] = tScore + manhattanDistance(coords, last)
                        openSet += ext
                    }
                }
        }

        return MAX_VALUE
    }
}
