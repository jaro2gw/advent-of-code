package me.pjaronski.aoc.utils

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class StringsKtTest {
    @Test
    fun `should pad around empty list`() {
        assertContentEquals(
            expected = listOf("..", ".."),
            actual = emptyList<String>().padAround('.')
        )
    }

    @Test
    fun `should pad around singleton list`() {
        assertContentEquals(
            expected = listOf("...", ".X.", "..."),
            actual = listOf("X").padAround('.')
        )
    }

    @Test
    fun `should pad around list with multiple items of the same length`() {
        assertContentEquals(
            expected = listOf(".....", ".123.", ".456.", ".789.", "....."),
            actual = listOf("123", "456", "789").padAround('.')
        )
    }

    @Test
    fun `should align and pad around list with multiple items of different lengths`() {
        assertContentEquals(
            expected = listOf(".......", ".1234..", ".56789.", "......."),
            actual = listOf("1234", "56789").padAround('.')
        )
    }
}