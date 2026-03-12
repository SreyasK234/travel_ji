package com.example.travelji.view.composables.app_pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodListView(modifier: Modifier){
    LazyColumn (
        modifier = modifier.padding(16.dp)
    ) {
        items(10){
            PlaceDetailCard("Food Row")
        }
    }
}