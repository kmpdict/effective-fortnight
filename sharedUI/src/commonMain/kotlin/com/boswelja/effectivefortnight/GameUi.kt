package com.boswelja.effectivefortnight

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.boswelja.effectivefortnight.sharedui.resources.Res
import com.boswelja.effectivefortnight.sharedui.resources.ic_correct
import com.boswelja.effectivefortnight.sharedui.resources.ic_incorrect
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

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
        ScoreCard(score, modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))
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
