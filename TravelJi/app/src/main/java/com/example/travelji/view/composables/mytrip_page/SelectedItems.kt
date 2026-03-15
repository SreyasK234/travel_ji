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
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
    val lighterPurpleGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF9C27B0), // Lighter Dark Purple
            Color(0xFFCE93D8), // Lighter Medium Purple
            Color(0xFFF3E5F5)  // Very Light Purple
        )
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Places", "Food", "Hidden Gems")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lighterPurpleGradient)
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                contentColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Color.White
                    )
                },
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                color = if (selectedTabIndex == index) Color.White else Color.White.copy(alpha = 0.6f)
                            )
                        }
                    )
                }
            }

            val currentList = when (selectedTabIndex) {
                0 -> viewModel.selectedPlaces
                1 -> viewModel.selectedFoodPlaces
                else -> viewModel.selectedHiddenGems
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(currentList) { item ->
                    PlaceDetailCard(
                        cardItemPojo = item,
                        isChecked = true,
                        onCheckedChange = { isChecked ->
                            if (!isChecked) {
                                when (selectedTabIndex) {
                                    0 -> viewModel.selectedPlaces.remove(item)
                                    1 -> viewModel.selectedFoodPlaces.remove(item)
                                    2 -> viewModel.selectedHiddenGems.remove(item)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
