package com.example.travelji.view.composables.login_pages.home_page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelji.model.HomeCardItem
import com.example.travelji.ui.theme.TravelJiTheme

@Composable
fun HomeScreen() {

    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedCity by rememberSaveable { mutableStateOf("Hyderabad") }
    val cities = listOf("Bengaluru", "Hyderabad", "Mumbai")

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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = selectedCity,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Icon(
                            imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth(0.87f)
                        .background(Color(0xFFAB47BC))
                ) {
                    cities.forEach { city ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = city,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                            },
                            onClick = {
                                selectedCity = city
                                expanded = false
                            }
                        )
                    }
                }
            }

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

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    TravelJiTheme{
        HomeScreen()
    }
}
