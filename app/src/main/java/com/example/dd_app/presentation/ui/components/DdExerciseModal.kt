package com.example.dd_app.presentation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.core.convert.DistanceConvert
import com.example.dd_app.core.enums.ExerciseType
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun DdExerciseModal(
    isExerciseStarted: Boolean,
    onExerciseTypeSelect: (ExerciseType) -> Unit,
    exerciseType: ExerciseType,
    bottomPadding: Dp,
    onExerciseStart: () -> Unit,
    distanceMeters: Int,
    onFinish: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
            .background(Colors.White)
            .fillMaxWidth()
            .animateContentSize(),
    ) {
        if (!isExerciseStarted) ExerciseSelectionContent(
            onExerciseTypeSelect = onExerciseTypeSelect,
            exerciseType = exerciseType,
            bottomPadding = bottomPadding,
            onExerciseStart = onExerciseStart
        ) else ExerciseInProgressContent(
            distanceMeters = distanceMeters,
            onFinish = onFinish,
            bottomPadding = bottomPadding,
            exerciseTypeTitle = exerciseType.title
        )
    }
}