package com.boswelja.effectivefortnight

import kotlin.test.ExperimentalKotlinTestApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GameStateTest {

    @Test
    fun score() {
        // Initialise a game
        val game = GameState()

        // Submit a correct value increments correct counter
        game.startGame(
            GameConfiguration(
                fromKana = TargetKana.Hiragana,
                toKana = TargetKana.Romaji
            )
        )
        game.presentedOptions.value.let { options ->
            assertNotNull(options)

            val targetTriplet = TRIPLETS.first { it.hiragana.toString() == options.target }
            game.checkSelection(targetTriplet.romaji)

            assertEquals(Score(1u, 0u), game.score.value)
        }

        // Submit an incorrect value increments incorrect counter
        game.startGame(
            GameConfiguration(
                fromKana = TargetKana.Hiragana,
                toKana = TargetKana.Romaji
            )
        )
        game.presentedOptions.value.let { options ->
            assertNotNull(options)

            val targetTriplet = TRIPLETS.first { it.hiragana.toString() == options.target }
            game.checkSelection((options.options - targetTriplet.romaji).first())

            assertEquals(Score(0u, 1u), game.score.value)
        }

        // Submit bad data increments incorrect counter
        game.startGame(
            GameConfiguration(
                fromKana = TargetKana.Hiragana,
                toKana = TargetKana.Romaji
            )
        )
        game.presentedOptions.value.let { options ->
            assertNotNull(options)

            game.checkSelection("Hello, world!")

            assertEquals(Score(0u, 1u), game.score.value)
        }

        // Submit empty data increments incorrect counter
        game.startGame(
            GameConfiguration(
                fromKana = TargetKana.Hiragana,
                toKana = TargetKana.Romaji
            )
        )
        game.presentedOptions.value.let { options ->
            assertNotNull(options)

            game.checkSelection("")

            assertEquals(Score(0u, 1u), game.score.value)
        }

        // Score is 0 after startGame
        game.startGame()
        assertEquals(Score(0u, 0u), game.score.value)

        // Scoring advances the triplet presented
        game.startGame()
        game.presentedOptions.value.let { options ->
            assertNotNull(options)

            game.checkSelection("")

            assertNotEquals(options, game.presentedOptions.value)
        }

        // Scoring the last triplet clears the presented options
        game.startGame()
        repeat(TRIPLETS.size) {
            game.checkSelection("")
        }
        assertNull(game.presentedOptions.value)
    }

    @OptIn(ExperimentalKotlinTestApi::class)
    @Test
    fun presentedOptions() {
        // Initialise a game
        val game = GameState()

        // presentedOptions always contains the correct option in the list
        TargetKana.entries.forEach { fromPermutation ->
            TargetKana.entries.forEach { toPermutation ->
                game.startGame(
                    GameConfiguration(
                        fromKana = fromPermutation,
                        toKana = toPermutation
                    )
                )
                repeat(TRIPLETS.size) {
                    val options = requireNotNull(game.presentedOptions.value)
                    val targetTriplet = TRIPLETS.first {
                        when (fromPermutation) {
                            TargetKana.Romaji -> it.romaji == options.target
                            TargetKana.Hiragana -> it.hiragana.toString() == options.target
                            TargetKana.Katakana -> it.katakana.toString() == options.target
                        }
                    }
                    assertTrue(options.options.contains(targetTriplet.toTargetKana(toPermutation))) {
                        "Options $options did not contain value for $targetTriplet!"
                    }
                }
            }
        }
    }
}
