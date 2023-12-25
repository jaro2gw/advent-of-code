package me.pjaronski.aoc.v23.day13.mirror

import me.pjaronski.aoc.utils.DynamicList

object MirrorFinder {
    private val symmetry: DynamicList<IntArray> = DynamicList.of { mirror ->
        val upper = 2 * mirror - 1
        IntArray(mirror) { upper - it }
    }

    private fun symmetric(line: String, mirror: Int): Boolean = symmetry[mirror]
        .withIndex()
        .filter { (idx, xdi) -> idx in line.indices && xdi in line.indices }
        .all { (idx, xdi) -> line[idx] == line[xdi] }

    private fun smudges(line: String, mirror: Int): Int = symmetry[mirror]
        .withIndex()
        .filter { (idx, xdi) -> idx in line.indices && xdi in line.indices }
        .count { (idx, xdi) -> line[idx] != line[xdi] }

    fun findMirror(lines: List<String>): Int? {
        for (mirror in 1 until lines[0].length) {
            if (lines.all { symmetric(it, mirror) }) return mirror
        }
        return null
    }

    fun findSmudge(lines: List<String>): Int? {
        for (mirror in 1 until lines[0].length) {
            if (lines.sumOf { smudges(it, mirror) } == 1) return mirror
        }
        return null
    }
}
