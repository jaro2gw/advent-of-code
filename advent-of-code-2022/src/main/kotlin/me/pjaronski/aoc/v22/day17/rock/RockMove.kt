package me.pjaronski.aoc.v22.day17.rock

enum class RockMove(val adjust: (Int) -> Int) {
    LEFT({ it - 1 }),
    RIGHT({ it + 1 });
}
