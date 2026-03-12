package com.example.travelji.view.composables.login_pages.home_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelji.model.HomeCardItem

@Composable
fun HomeScreen() {
    val homeCardItems = listOf(
        HomeCardItem("Recommended Places", Icons.Default.Place),
        HomeCardItem("Recommended Restaurants", Icons.Default.Restaurant),
        HomeCardItem("Hidden Gems", Icons.Default.Explore),
        HomeCardItem("My Trip", Icons.Default.Backpack),
        HomeCardItem("Profile", Icons.Default.Person)
    )

    // Define the purple gradient
    val purpleGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF6A1B9A), // Dark Purple
            Color(0xFFAB47BC), // Medium Purple
            Color(0xFFE1BEE7)  // Light Purple
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(purpleGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Explore the World",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Find your next adventure",
//                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(homeCardItems) { item ->
                    HomeCard(item)
                }

            }

        }
    }
}
