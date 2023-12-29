package me.pjaronski.aoc.v23.day14.platform

object RockPlatformSpinner {

    fun spin(rocks: RockPlatform, cycles: Int): RockPlatform {
        val cache = mutableMapOf(rocks to 0)
        val history = mutableListOf(rocks)

        var platform = rocks
        for (cycle in 1..cycles) {
            platform = platform.spin()

            history += platform
            val start = cache.put(platform, cycle)
            if (start != null) {
                val length = cycle - start
                val offset = (cycles - start) % length

                platform = history[start + offset]
                break
            }
        }

        return platform
    }
}
