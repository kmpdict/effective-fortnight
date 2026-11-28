package com.boswelja.effectivefortnight

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random
import kotlin.time.Clock

class GameState {
    var gameConfig: GameConfiguration = GameConfiguration(
        fromKana = TargetKana.Hiragana,
        toKana = TargetKana.Romaji
    )

    private val random = Random(Clock.System.now().epochSeconds)

    private var remainingTriplets = TRIPLETS

    private val currentTriplet = MutableStateFlow<Triplet?>(null)
    private val _presentedOption = MutableStateFlow<PresentedOptions?>(null)
    private val _score = MutableStateFlow(Score(0u, 0u))

    val score: StateFlow<Score> = _score

    val presentedOptions: StateFlow<PresentedOptions?> = _presentedOption

    fun checkSelection(selection: String) {
        val currentTriplet = currentTriplet.value ?: return
        _score.update { currentScore ->
            if (selection == currentTriplet.toTargetKana(gameConfig.toKana)) {
                currentScore.incrementCorrect()
            } else {
                currentScore.incrementIncorrect()
            }
        }
        advanceToNextTriplet()
    }

    fun startGame(newConfig: GameConfiguration? = null) {
        currentTriplet.value = null
        _presentedOption.value = null
        if (newConfig != null) gameConfig = newConfig
        _score.update { Score(0u, 0u) }
        remainingTriplets = TRIPLETS
        advanceToNextTriplet()
    }

    private fun advanceToNextTriplet() {
        if (remainingTriplets.isEmpty()) {
            currentTriplet.value = null
            _presentedOption.value = null
            return
        }
        val newTriplet = remainingTriplets.random(random)
        currentTriplet.value = newTriplet
        _presentedOption.value = PresentedOptions(
            target = newTriplet.toTargetKana(gameConfig.fromKana),
            options = pickOptionsIncluding(4, newTriplet)
        )
        remainingTriplets -= newTriplet
    }

    private fun pickOptionsIncluding(count: Int, include: Triplet): Set<String> {
        val candidates = TRIPLETS - include
        val result = candidates
            .takeRandom(count - 1)
            .map { it.toTargetKana(gameConfig.toKana) } + include.toTargetKana(gameConfig.toKana)
        return result.shuffled().toSet()
    }
}

internal fun Triplet.toTargetKana(target: TargetKana): String {
    return when (target) {
        TargetKana.Romaji -> romaji
        TargetKana.Hiragana -> hiragana.toString()
        TargetKana.Katakana -> katakana.toString()
    }
}

data class PresentedOptions(
    val target: String,
    val options: Set<String>
)

data class Score(
    val correct: UInt,
    val incorrect: UInt
) {
    fun incrementCorrect(): Score {
        return copy(correct = correct + 1u)
    }

    fun incrementIncorrect(): Score {
        return copy(incorrect = incorrect + 1u)
    }
}

enum class TargetKana {
    Romaji,
    Hiragana,
    Katakana
}

data class GameConfiguration(
    val fromKana: TargetKana,
    val toKana: TargetKana,
)
