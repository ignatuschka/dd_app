package com.example.dd_app.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.theme.Colors
import kotlinx.coroutines.launch

data class TabItem(
    val title: String,
    val content: @Composable () -> Unit
)

@Composable
fun ActivityPage(navToUserActivity: (Int) -> Unit, navToMyActivity: (Int) -> Unit) {
    val tabs = listOf(
        TabItem(stringResource(R.string.my)) { MyActivityTab(navToMyActivity = navToMyActivity) },
        TabItem(stringResource(R.string.users)) { UsersActivityTab(navToUserActivity = navToUserActivity) }
    )
    val pagerState = rememberPagerState(0, pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(
            indicator = { tabPositions ->
                if (pagerState.currentPage < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(
                            tabPositions[pagerState.currentPage],
                        ),
                        height = 2.dp,
                        color = Colors.Purple
                    )
                }
            },
            containerColor = Colors.White,
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selectedContentColor = Colors.Purple,
                    unselectedContentColor = Colors.Gray,
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            tab.title,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Medium,
                                lineHeight = 16.sp,
                            )
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .background(color = Colors.Surface)
        ) { page -> tabs[page].content() }
    }
}