package com.boswelja.effectivefortnight

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GameUi(
    state: GameState,
    modifier: Modifier = Modifier
) {
    val presentedOptions by state.presentedOptions.collectAsState()
    val score by state.score.collectAsState()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Correct: ${score.correct}")
        Text("Incorrect: ${score.incorrect}")
        AnimatedContent(presentedOptions) {
            Column {
                if (it == null) {
                    Button(onClick = { state.startGame() }) {
                        Text("Start game")
                    }
                } else {
                    Text(it.target, style = MaterialTheme.typography.displayLargeEmphasized)

                    FlowRow {
                        it.options.forEach { option ->
                            Button(onClick = { state.checkSelection(option) }) { Text(option) }
                        }
                    }
                }
            }
        }
    }
}
