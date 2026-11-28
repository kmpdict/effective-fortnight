package com.boswelja.effectivefortnight

import kotlin.random.Random
import kotlin.time.Clock

private val random = Random(Clock.System.now().epochSeconds)

fun <T> Collection<T>.takeRandom(count: Int, random: Random = com.boswelja.effectivefortnight.random): Set<T> {
    require(count <= this.size) { "Cannot grab more items than in collection!" }
    val randSet = mutableSetOf<T>()
    while (randSet.size < count) {
        randSet += this.random(random)
    }
    return randSet
}
