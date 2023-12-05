package me.pjaronski.aoc.v23.day02.game

import me.pjaronski.aoc.utils.EnumArray
import me.pjaronski.aoc.utils.NATURAL_NUMBER_PATTERN
import kotlin.math.max

class Game(
    val id: Int,
    private val cubes: EnumArray<CubeColor, Int>
) {
    companion object {
        private val GAME_ID_REGEX = Regex("Game $NATURAL_NUMBER_PATTERN")
        private val GAME_PEEK_CUBE_REGEX = Regex("$NATURAL_NUMBER_PATTERN (red|green|blue)")

        fun parse(line: String): Game {
            val (idPart, peeksPart) = line.split(": ")

            val id = GAME_ID_REGEX.find(idPart)!!.groupValues[1].toInt()
            val cubes = EnumArray.of<CubeColor, Int> { 0 }

            for (peekPart in peeksPart.split("; ")) {
                for (cubePart in peekPart.split(", ")) {
                    val (_, countPart, colorPart) = GAME_PEEK_CUBE_REGEX.find(cubePart)!!.groupValues

                    val count = countPart.toInt()
                    val color = CubeColor.valueOf(colorPart)

                    cubes[color] = max(cubes[color], count)
                }
            }

            return Game(id, cubes)
        }
    }

    operator fun get(color: CubeColor): Int = cubes[color]

    val power: Int = cubes.values().reduce(Int::times)
}
