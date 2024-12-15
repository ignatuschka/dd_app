package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dd_app.core.enums.ExerciseType

@Composable
fun ExerciseTypeLazyRow(
    modifier: Modifier = Modifier,
    onExerciseTypeSelect: (ExerciseType) -> Unit,
    exerciseType: ExerciseType,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(ExerciseType.entries.toTypedArray(), key = { it }) { item ->
            ExerciseTypeCard(
                onExerciseTypeSelect = onExerciseTypeSelect,
                exerciseType = exerciseType,
                item = item
            )
        }
    }
}