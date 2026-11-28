package com.boswelja.effectivefortnight

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun colorScheme(darkTheme: Boolean): ColorScheme {
    return when {
        darkTheme -> darkScheme
        else -> lightScheme
    }
}
