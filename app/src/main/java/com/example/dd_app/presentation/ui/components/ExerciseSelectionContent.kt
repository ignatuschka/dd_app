package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.core.enums.ExerciseType
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun ExerciseSelectionContent(
    modifier: Modifier = Modifier,
    onExerciseTypeSelect: (ExerciseType) -> Unit,
    exerciseType: ExerciseType,
    bottomPadding: Dp,
    onExerciseStart: () -> Unit,
) {
    val bottomModifier =
        Modifier
            .padding(top = 32.dp, bottom = 32.dp + bottomPadding, start = 16.dp, end = 16.dp)
            .fillMaxWidth()

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.padding(top = 32.dp, bottom = 24.dp),
            text = stringResource(R.string.letsgo),
            style = MaterialTheme.typography.headlineSmall,
            color = Colors.Dark,
        )
        ExerciseTypeLazyRow(
            onExerciseTypeSelect = onExerciseTypeSelect,
            exerciseType = exerciseType
        )
        DdButton(
            modifier = bottomModifier,
            text = stringResource(R.string.getStarted),
            onClick = { onExerciseStart() })
    }
}