package com.boswelja.effectivefortnight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import org.jetbrains.compose.resources.painterResource

@PreviewLightDark
@PreviewFontScale
@Composable
fun ScoreCard(
    @PreviewParameter(ScorePreviewParameterProvider::class) score: Score,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ScoreComponent(
            contentDescription = "${score.correct} correct answers",
            position = Position.Start,
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_correct),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = score.correct.toString(),
                style = MaterialTheme.typography.displayMediumEmphasized
            )
        }
        ScoreComponent(
            contentDescription = "${score.incorrect} incorrect answers",
            position = Position.End,
            color = MaterialTheme.colorScheme.errorContainer
        ) {
            Text(
                text = score.incorrect.toString(),
                style = MaterialTheme.typography.displayMediumEmphasized
            )
            Icon(
                painter = painterResource(Res.drawable.ic_incorrect),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
internal fun ScoreComponent(
    contentDescription: String,
    position: Position,
    color: Color,
    modifier: Modifier = Modifier,
    contentColor: Color = contentColorFor(color),
    content: @Composable RowScope.() -> Unit
) {
    val shape = when (position) {
        Position.Start -> MaterialTheme.shapes.extraLarge.copy(
            topEnd = MaterialTheme.shapes.small.topEnd,
            bottomEnd = MaterialTheme.shapes.small.bottomEnd
        )
        Position.Middle -> MaterialTheme.shapes.small
        Position.End -> MaterialTheme.shapes.extraLarge.copy(
            topStart = MaterialTheme.shapes.small.topStart,
            bottomStart = MaterialTheme.shapes.small.bottomStart
        )
    }
    Surface(
        shape = shape,
        color = color,
        contentColor = contentColor,
        modifier = modifier.semantics {
            this.contentDescription = contentDescription
        }
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides MaterialTheme.typography.displayMediumEmphasized
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                content = content
            )
        }

    }
}

internal enum class Position {
    Start,
    Middle,
    End
}

class ScorePreviewParameterProvider : PreviewParameterProvider<Score> {
    override val values: Sequence<Score> = sequenceOf(
        Score(0u, 0u),
        Score(10u, 0u),
        Score(0u, 10u),
        Score(5u, 5u)
    )
}
