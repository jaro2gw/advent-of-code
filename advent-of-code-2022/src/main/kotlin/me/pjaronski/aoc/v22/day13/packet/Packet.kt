package me.pjaronski.aoc.v22.day13.packet

sealed class Packet : Comparable<Packet> {
    abstract override infix operator fun compareTo(other: Packet): Int
}
