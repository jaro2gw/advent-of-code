package me.pjaronski.aoc.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MathKtTest {
    @Test
    fun `should calculate gcd correctly`() {
        assertEquals(1, gcd(2, 3))
        assertEquals(1, gcd(3, 2))

        assertEquals(1, gcd(3, 5))
        assertEquals(1, gcd(5, 3))

        assertEquals(1, gcd(5, 7))
        assertEquals(1, gcd(7, 5))

        assertEquals(1, gcd(7, 11))
        assertEquals(1, gcd(11, 7))

        assertEquals(2, gcd(2, 4))
        assertEquals(2, gcd(4, 2))

        assertEquals(2, gcd(4, 6))
        assertEquals(2, gcd(6, 4))

        assertEquals(2, gcd(6, 8))
        assertEquals(2, gcd(8, 6))

        assertEquals(3, gcd(6, 9))
        assertEquals(3, gcd(9, 6))
    }

    @Test
    fun `should calculate lcm correctly`() {
        assertEquals(12, lcm(3, 4))
        assertEquals(12, lcm(4, 3))

        assertEquals(15, lcm(5, 3))
        assertEquals(15, lcm(3, 5))

        assertEquals(35, lcm(7, 5))
        assertEquals(35, lcm(5, 7))

        assertEquals(72, lcm(24, 18))
        assertEquals(72, lcm(18, 24))
    }
}
