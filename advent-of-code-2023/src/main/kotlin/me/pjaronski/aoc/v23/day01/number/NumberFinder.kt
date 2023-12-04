package me.pjaronski.aoc.v23.day01.number

class NumberFinder {
    private fun find(
        line: String,
        index: Int,
        nodes: Collection<NumberCharacterNode>,
        initial: Collection<NumberCharacterNode> = nodes
    ): Int {
        val char = line[index]
        val next = initial.toMutableList()
        for (node in nodes) if (node.digit == char) when (node.next) {
            is NumberCharacterNode -> {
                next += node.next
            }

            is NumberFound -> {
                return node.next.number
            }
        }
        return find(line, index + 1, next, initial)
    }

    fun find(line: String, wrappers: Collection<NumberWrapper>): Pair<Int, Int> {
        val chainsForward = wrappers.map { NumberCharacterChain.from(it) }
        val firstNumberForward = find(line, 0, chainsForward)

        val chainsBackward = wrappers.map { (number, string) -> NumberWrapper(number, string.reversed()) }
            .map { NumberCharacterChain.from(it) }
        val firstNumberBackward = find(line.reversed(), 0, chainsBackward)

        return firstNumberForward to firstNumberBackward
    }
}
