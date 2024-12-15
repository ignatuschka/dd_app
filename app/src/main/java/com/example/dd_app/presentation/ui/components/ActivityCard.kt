package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.dd_app.presentation.ui.theme.Colors

@Composable
fun ActivityCard(
    modifier: Modifier = Modifier,
    distance: String = "",
    userName: String = "",
    exerciseTime: String = "",
    exerciseType: String = "",
    exerciseDate: String = "",
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier,
        enabled = true,
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(Colors.White),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = distance,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Colors.Dark,
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = exerciseTime,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Colors.Dark
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = userName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Colors.Purple,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = exerciseType,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Colors.Dark
                )
                Text(
                    text = exerciseDate,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Colors.Gray
                )
            }
        }
    }
}