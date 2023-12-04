package me.pjaronski.aoc.v22.day13

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.split
import me.pjaronski.aoc.v22.day13.packet.PacketData
import me.pjaronski.aoc.v22.day13.packet.PacketList
import me.pjaronski.aoc.v22.day13.packet.PacketParser
import me.pjaronski.aoc.v22.day13.packet.PacketTokenizer

fun main() = present(Solution13)

object Solution13 : Solution<Int, Int> {
    override fun part1(input: Input): Int {
        val tokenizer = PacketTokenizer()
        val parser = PacketParser(tokenizer)

        return split(input)
            .mapIndexed { idx, (s1, s2) ->
                val p1 = parser.parse(s1)
                val p2 = parser.parse(s2)

                return@mapIndexed Triple(
                    idx + 1,
                    p1,
                    p2
                )
            }
            .filter { (_, p1, p2) -> p1 < p2 }
            .sumOf { (idx, _, _) -> idx }
    }

    override fun part2(input: Input): Int {
        val tokenizer = PacketTokenizer()
        val parser = PacketParser(tokenizer)

        val div2 = PacketList(packet = PacketList(packet = PacketData(2)))
        val div6 = PacketList(packet = PacketList(packet = PacketData(6)))

        val packets = split(input)
            .flatten()
            .map { parser.parse(it) }
            .toMutableList()
            .apply {
                add(div2)
                add(div6)
            }
            .sorted()

        val idx2 = packets.indexOf(div2) + 1
        val idx6 = packets.indexOf(div6) + 1

        return idx2 * idx6
    }
}
