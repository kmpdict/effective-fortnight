package com.boswelja.effectivefortnight

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ListExtTest {
    @Test
    fun takeRandom() {
        // Take 0 of 0 list
        assertTrue(emptyList<String>().takeRandom(0).isEmpty())

        // Take 1 of 1 list
        assertEquals(setOf("Test"), listOf("Test").takeRandom(1))

        // Take 4 of 4 list
        assertEquals(4, listOf(1, 2, 3, 4).takeRandom(4).size)
        assertEquals(setOf(1, 2, 3, 4), listOf(1, 2, 3, 4).takeRandom(4))

        // Take more than the contents of the list
        assertFailsWith(IllegalArgumentException::class) {
            emptyList<Int>().takeRandom(1)
        }

        // Consecutive calls return different results
        val rand = Random(1)
        assertEquals(
            3,
            (0..3)
                .map { listOf(1, 2, 3, 4).takeRandom(3, rand) }
                .toSet()
                .size
        )
    }
}
