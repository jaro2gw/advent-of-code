package me.pjaronski.aoc.v22.day07

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v22.day07.file.system.FileSystemDirectory
import me.pjaronski.aoc.v22.day07.file.system.FileSystemReconstructor

fun main() = present(Solution07)

object Solution07 : Solution<Long, Long> {
    private fun directories(directory: FileSystemDirectory): Sequence<FileSystemDirectory> = sequence {
        yield(directory)
        directory.mapNotNull { it as? FileSystemDirectory }.forEach {
            yieldAll(directories(it))
        }
    }

    override fun part1(input: Input): Long {
        val root = FileSystemReconstructor.reconstruct(input.lines())
        return directories(root).map { it.size() }
            .filter { it <= 100_000L }
            .sum()
    }

    override fun part2(input: Input): Long {
        val root = FileSystemReconstructor.reconstruct(input.lines())
        val deviceSpace = 70_000_000L
        val requiredSpace = 30_000_000L
        val maxUsedSpace = deviceSpace - requiredSpace
        val usedSpace = root.size()
        return if (usedSpace <= maxUsedSpace) 0
        else {
            val minimumSize = usedSpace - maxUsedSpace
            directories(root).map { it.size() }
                .filter { it >= minimumSize }
                .min()
        }
    }
}
