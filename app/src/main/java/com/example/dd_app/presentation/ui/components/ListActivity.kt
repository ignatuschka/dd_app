package com.example.dd_app.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dd_app.R
import com.example.dd_app.core.convert.DistanceConvert
import com.example.dd_app.core.convert.timeAgo
import com.example.dd_app.core.convert.timeDifference
import com.example.dd_app.presentation.ui.theme.Colors
import com.example.dd_app.presentation.viewmodel.ActivitiesWithTitles
import java.time.LocalDateTime

@Composable
fun ListActivity(
    itemList: List<ActivitiesWithTitles>,
    showUsername: Boolean = false,
    navToActivity: (Int) -> Unit
) {
    val now = LocalDateTime.now()
    val textModifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
    val textStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal)
    val textColor = Colors.Gray

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(items = itemList, key = { it.item.id }) { item ->
            Column {
                if (item.title != null) Text(
                    modifier = textModifier,
                    text = item.title,
                    style = textStyle,
                    color = textColor,
                )
                ActivityCard(
                    modifier = Modifier.padding(bottom = 16.dp),
                    distance = DistanceConvert.toShortMeters(item.item.distanceMeters),
                    exerciseDate = item.item.exerciseStart.timeAgo(
                        now,
                        stringResource(R.string.ago),
                        stringResource(R.string.rightNow)
                    ),
                    exerciseType = item.item.exerciseType.title,
                    exerciseTime = item.item.exerciseStart.timeDifference(

                        item.item.exerciseEnd
                    ),
                    userName = if (showUsername && item.item.userName != null) item.item.userName else "",
                    onClick = { navToActivity(item.item.id) }
                )
            }
        }
    }
}