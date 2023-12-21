package me.pjaronski.aoc.v23.day12.onsen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class OnsenValidatorTest {
    @TestFactory
    fun `should return single possibility when everything is known`() = listOf(
        "#.#.### 1,1,3",
        ".#...#....###. 1,1,3",
        ".#.###.#.###### 1,3,1,6",
        "####.#...#... 4,1,1",
        "#....######..#####. 1,6,5",
        ".###.##....# 3,2,1",
    ).map { onsen ->
        dynamicTest("should calculate onsen possibilities correctly [$onsen]") {
            val actual = OnsenValidator.possibilities(onsen)
            assertEquals(1L, actual)
        }
    }

    @TestFactory
    fun `should calculate onsen possibilities correctly`() = listOf(
        "???.### 1,1,3" to 1L,
        ".??..??...?##. 1,1,3" to 4L,
        "?#?#?#?#?#?#?#? 1,3,1,6" to 1L,
        "????.#...#... 4,1,1" to 1L,
        "????.######..#####. 1,6,5" to 4L,
        "?###???????? 3,2,1" to 10L,
    ).map { (onsen, expected) ->
        dynamicTest("should calculate onsen possibilities correctly [$onsen]") {
            val actual = OnsenValidator.possibilities(onsen)
            assertEquals(expected, actual)
        }
    }

    @TestFactory
    fun `should calculate extended onsen possibilities correctly`() = listOf(
        "???.###. 1,1,3" to 1L,
        "???.#### 1,1,3" to 0L,
        ".???.###. 1,1,3" to 1L,
        ".???.#### 1,1,3" to 0L,
        "#???.###. 1,1,3" to 2L,
        "#???.#### 1,1,3" to 0L,
        ".???.### 1,1,3" to 1L,
        "#???.### 1,1,3" to 2L,

        ".??..??...?##.. 1,1,3" to 4L,
        ".??..??...?##.# 1,1,3" to 0L,
        "..??..??...?##.. 1,1,3" to 4L,
        "..??..??...?##.# 1,1,3" to 0L,
        "#.??..??...?##.. 1,1,3" to 4L,
        "#.??..??...?##.# 1,1,3" to 0L,
        "..??..??...?##. 1,1,3" to 4L,
        "#.??..??...?##. 1,1,3" to 4L,

        "?#?#?#?#?#?#?#? 1,3,1,6" to 1L,

        "????.#...#... 4,1,1" to 1L,

        "????.######..#####. 1,6,5" to 4L,

        "?###???????? 3,2,1" to 10L,
    ).map { (onsen, expected) ->
        dynamicTest("should calculate onsen possibilities correctly [$onsen]") {
            val actual = OnsenValidator.possibilities(onsen)
            assertEquals(expected, actual)
        }
    }
}
