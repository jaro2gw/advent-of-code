package me.pjaronski.aoc.v23.day04.scratchcard

data class ScratchCard(
    val id: Int,
    val winners: Int
) {
    companion object {

        private val SPACE_REGEX = Regex(" +")

        fun parse(line: String): ScratchCard {
            val (idPart, numbersPart) = line.split(": ")

            val id = idPart.split(SPACE_REGEX)[1].toInt()

            val (winningNumbers, printedNumbers) = numbersPart.split(" | ")
                .map { nums ->
                    nums.split(SPACE_REGEX)
                        .filter { it.isNotEmpty() }
                        .map { it.toInt() }
                        .toSet()
                }
            val winners = printedNumbers.count { it in winningNumbers }

            return ScratchCard(id, winners)
        }
    }
}
