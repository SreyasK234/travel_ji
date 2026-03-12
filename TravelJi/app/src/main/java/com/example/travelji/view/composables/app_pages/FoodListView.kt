package com.example.travelji.view.composables.app_pages

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelji.model.CardItemPojo

@Composable
fun FoodListView(modifier: Modifier, dataFood: List<CardItemPojo>){
    val foodList = dataFood
    Log.d("Food List", foodList.toString())
    LazyColumn (
        modifier = modifier.padding(16.dp)
    ) {
        items(foodList.size){
            PlaceDetailCard("Food Row", foodList[it])
        }
    }
}