package com.boswelja.effectivefortnight

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform