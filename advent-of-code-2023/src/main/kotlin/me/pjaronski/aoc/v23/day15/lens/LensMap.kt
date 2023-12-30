package me.pjaronski.aoc.v23.day15.lens

class LensMap {
    private val boxes = Array(256) { LinkedHashMap<String, Int>() }

    fun add(lens: String, length: Int) {
        val hash = LensHash.of(lens)
        boxes[hash][lens] = length
    }

    fun remove(lens: String) {
        val hash = LensHash.of(lens)
        boxes[hash] -= lens
    }

    fun power(): Int = boxes.withIndex()
        .sumOf { (box, map) ->
            map.values
                .withIndex()
                .sumOf { (slot, length) ->
                    (box + 1) * (slot + 1) * length
                }
        }
}
