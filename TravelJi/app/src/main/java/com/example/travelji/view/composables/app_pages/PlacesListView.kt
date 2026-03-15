package com.example.travelji.view.composables.app_pages

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelji.model.CardItemPojo
import com.example.travelji.viewmodel.AppViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlacesListView(modifier: Modifier, data: List<CardItemPojo>, appViewModel: AppViewModel) {
    val lighterPurpleGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF9C27B0), // Lighter Dark Purple
            Color(0xFFCE93D8), // Lighter Medium Purple
            Color(0xFFF3E5F5)  // Very Light Purple
        )
    )

    Log.d("ListView", data.toString())

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(lighterPurpleGradient)
    ){
        LazyColumn(
            modifier = modifier.padding(16.dp)
        ) {
            stickyHeader {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.2f)
                    ),

                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f)),

                ){
                    Text(
                        text = "Recommended Places",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            if(data.isEmpty()){
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }else{
                items(data) {item ->
                    val isSelected = appViewModel.selectedPlaces.contains(item)
                    PlaceDetailCard(
                        cardItemPojo = item,
                        isChecked = isSelected,
                        onCheckedChange = { checked ->
                            if (checked) appViewModel.selectedPlaces.add(item)
                            else appViewModel.selectedPlaces.remove(item)
                        }
                    )
                }
            }
        }
    }
}
