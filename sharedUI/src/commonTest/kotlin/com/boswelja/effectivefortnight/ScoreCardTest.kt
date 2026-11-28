package com.boswelja.effectivefortnight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class ScoreCardTest {

    @Test
    fun scoreCardDisplaysScores() = runComposeUiTest {
        var score by mutableStateOf(Score(0u, 0u))
        setContent {
            ScoreCard(score)
        }

        // Incorrect score displays 0s
        score = Score(1u, 0u)
        onNodeWithText("0").assertIsDisplayed()

        // Correct score displays 0s
        score = Score(0u, 1u)
        onNodeWithText("0").assertIsDisplayed()

        // Arbitrary scores are displayed
        score = Score(100u, 321u)
        onNodeWithText("100").assertIsDisplayed()
        onNodeWithText("321").assertIsDisplayed()

        // Correct score displays max
        score = Score(UInt.MAX_VALUE, 0u)
        onNodeWithText(UInt.MAX_VALUE.toString()).assertIsDisplayed()

        // Incorrect score displays max
        score = Score(0u, UInt.MAX_VALUE)
        onNodeWithText(UInt.MAX_VALUE.toString()).assertIsDisplayed()
    }
}
