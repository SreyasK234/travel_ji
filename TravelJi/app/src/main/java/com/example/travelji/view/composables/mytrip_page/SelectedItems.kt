package com.example.travelji.view.composables.mytrip_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.travelji.view.composables.app_pages.PlaceDetailCard
import com.example.travelji.viewmodel.AppViewModel

@Composable
fun SelectedItemsScreen(modifier: Modifier, viewModel: AppViewModel) {

    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        "Restaurants",
        "Places",
        "Hidden Gems"
    )

    val lighterPurpleGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF9C27B0),
            Color(0xFFCE93D8),
            Color(0xFFF3E5F5)
        )
    )

    val list = when (selectedTab) {
        0 -> viewModel.selectedRestaurants
        1 -> viewModel.selectedPlaces
        else -> viewModel.selectedHiddenGems
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lighterPurpleGradient)
    ) {

        Column {

            TabRow(selectedTabIndex = selectedTab) {

                tabs.forEachIndexed { index, title ->

                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            LazyColumn(
                modifier = modifier.padding(16.dp)
            ) {

                items(list) { item ->

                    PlaceDetailCard(
                        cardItemPojo = item,
                        isChecked = true,
                        onCheckedChange = { },
                    )
                }
            }
        }
    }
}
