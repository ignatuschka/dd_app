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
            if (!isExerciseStarted) Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier.padding(top = 32.dp, bottom = 24.dp),
                    text = stringResource(R.string.letsgo),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Colors.Dark,
                )
                LazyRow {
                    itemsIndexed(
                        ExerciseType.entries.toTypedArray(),
                        key = { _, item -> item.name }) { index, item ->
                        Card(
                            modifier = Modifier
                                .padding(
                                    start = if (index == 0) 16.dp else 0.dp,
                                    end = if (index + 1 == ExerciseType.entries.size) 16.dp else 8.dp
                                )
                                .height(84.dp),
                            onClick = { onExerciseTypeSelect(item) },
                            enabled = true,
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(Colors.White),
                            border = if (item == exerciseType) BorderStroke(
                                width = 2.dp,
                                color = Colors.Purple
                            ) else BorderStroke(width = 1.dp, color = Colors.Gray)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier.padding(start = 16.dp, end = 10.dp),
                                    text = item.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Colors.Dark
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.activity_type),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
                DdButton(
                    modifier = Modifier
                        .padding(
                            top = 32.dp,
                            bottom = 32.dp + bottomPadding,
                            start = 16.dp,
                            end = 16.dp
                        )
                        .fillMaxWidth(),
                    text = stringResource(R.string.getStarted),
                    onClick = { onExerciseStart() }
                )
            } else Column(
                modifier = Modifier.padding(
                    top = 32.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 32.dp + bottomPadding
                ),
            ) {
                Text(
                    text = exerciseType.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Colors.Dark,
                )
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 32.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (distanceMeters > 1000) "${distanceMeters.toDouble() / 1000} км" else "$distanceMeters м",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal),
                        color = Colors.Dark
                    )
                    TimerText()
                }
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(56.dp),
                    onClick = onFinish,
                    colors = IconButtonDefaults.iconButtonColors(Colors.Purple)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.racing_flag),
                        contentDescription = "finish",
                        tint = Colors.White
                    )
                }
            }
        }
}