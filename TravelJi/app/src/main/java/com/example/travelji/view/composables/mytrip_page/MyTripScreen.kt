package com.example.travelji.view.composables.mytrip_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Diamond
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyTripPage(
    modifier: Modifier = Modifier,
    tripName: String = "Weekend Getaway",
    tripDate: String = "Oct 25 - Oct 27, 2024",
    selectedPlaces: List<TripItem> = emptyList(),
    restaurants: List<TripItem> = emptyList(),
    hiddenGems: List<TripItem> = emptyList()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GradientIndigo)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            TripHeader()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                contentPadding = PaddingValues(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Trip Info Card (Date & Name)
                item {
                    TripInfoCard(tripName, tripDate)
                }

                if (selectedPlaces.isNotEmpty()) {
                    item {
                        SectionHeader(
                            "Selected Places",
                            Icons.Outlined.LocationOn
                        )
                    }
                    items(selectedPlaces) { item ->
                        TripItemCard(item)
                    }
                }

                if (restaurants.isNotEmpty()) {
                    item {
                        SectionHeader(
                            "Restaurants",
                            Icons.Outlined.Restaurant
                        )
                    }
                    items(restaurants) { item ->
                        TripItemCard(item)
                    }
                }

                if (hiddenGems.isNotEmpty()) {
                    item {
                        SectionHeader(
                            "Hidden Gems",
                            Icons.Outlined.Diamond
                        )
                    }
                    items(hiddenGems) { item ->
                        TripItemCard(item, isHighlighted = true)
                    }
                }

                if (selectedPlaces.isEmpty() && restaurants.isEmpty() && hiddenGems.isEmpty()) {
                    item {
                        EmptyTripState()
                    }
                }
            }
        }
    }
}