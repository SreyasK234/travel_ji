package com.example.travelji.view.composables.app_pages

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelji.model.CardItemPojo

@Composable
fun PlacesListView(modifier: Modifier, data: List<CardItemPojo>) {

    val cardItems : List<CardItemPojo> = data

    Log.d("ListView", cardItems.toString())

    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        stickyHeader {
            Card(modifier = Modifier.padding(8.dp)) {
                Text("Recommended Places", fontSize = 30.sp)
            }
        }
        items(cardItems.size){
            PlaceDetailCard(cardItems[it].name, cardItems[it])
        }
    }
}