package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.core.convert.DistanceConvert
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun ExerciseInProgressContent(
    distanceMeters: Int,
    onFinish: () -> Unit,
    bottomPadding: Dp,
    exerciseTypeTitle: String
) {
    Column(
        modifier = Modifier.padding(
            top = 32.dp,
            start = 24.dp,
            end = 24.dp,
            bottom = 32.dp + bottomPadding
        ),
    ) {
        Text(
            text = exerciseTypeTitle,
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
                text = DistanceConvert.toShortMeters(distanceMeters),
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