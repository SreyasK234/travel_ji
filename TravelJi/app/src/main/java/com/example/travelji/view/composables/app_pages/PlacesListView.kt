package com.example.travelji.view.composables.app_pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlacesListView(modifier: Modifier) {
    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        stickyHeader {
            Card(modifier = Modifier.padding(8.dp)) {
                Text("Recommended Places", fontSize = 30.sp)
            }
        }
        items(5){
            PlaceDetailCard("Charminar")
        }
    }
}