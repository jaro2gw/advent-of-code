package me.pjaronski.aoc.utils

class DynamicList<T> private constructor(
    private val data: ArrayList<T> = ArrayList(),
    private val init: (Int) -> T
) : List<T> by data {
    companion object {
        fun <T> of(init: (Int) -> T) = DynamicList(init = init)
    }

    override operator fun get(index: Int): T {
        while (data.size <= index) {
            data += init(data.size)
        }
        return data[index]
    }
}
