package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.core.enums.ExerciseType
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun ExerciseTypeCard(
    modifier: Modifier = Modifier,
    onExerciseTypeSelect: (ExerciseType) -> Unit,
    exerciseType: ExerciseType,
    item: ExerciseType
) {
    Card(
        modifier = Modifier.height(84.dp),
        onClick = { onExerciseTypeSelect(item) },
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