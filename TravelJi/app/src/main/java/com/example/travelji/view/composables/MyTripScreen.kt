package com.example.travelji.view.composables

import com.example.travelji.R
import androidx.compose.foundation.BorderStroke
import com.example.travelji.MainActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Diamond
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MyTripScreen {
    companion object{
        private val PrimaryIndigo = Color(0xFF4F46E5)
        private val SecondaryIndigo = Color(0xFF6366F1)
        private val GemGold = Color(0xFFFFD700)
        private val GradientIndigo = Brush.verticalGradient(
            listOf(Color(0xFFEEF2FF), Color(0xFFE0E7FF))
        )


        /* --------------------------- DATA MODELS --------------------------- */

        data class TripItem(
            val id: Int,
            val name: String,
            val description: String,
            val rating: Double,
            val category: String, // "Place", "Restaurant", "Hidden Gem"
            val imageResId: Int? = null
        )

        /* --------------------------- SCREEN --------------------------- */

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
                            item { SectionHeader("Selected Places", Icons.Outlined.LocationOn) }
                            items(selectedPlaces) { item ->
                                TripItemCard(item)
                            }
                        }

                        if (restaurants.isNotEmpty()) {
                            item { SectionHeader("Restaurants", Icons.Outlined.Restaurant) }
                            items(restaurants) { item ->
                                TripItemCard(item)
                            }
                        }

                        if (hiddenGems.isNotEmpty()) {
                            item { SectionHeader("Hidden Gems", Icons.Outlined.Diamond) }
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

        /* --------------------------- COMPONENTS --------------------------- */

        @Composable
        private fun TripHeader() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "My Trip Plans",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.5).sp,
                        color = PrimaryIndigo
                    )
                )
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(4.dp)
                        .background(SecondaryIndigo, RoundedCornerShape(2.dp))
                )
            }
        }

        @Composable
        private fun TripInfoCard(name: String, date: String) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(24.dp),
                        spotColor =PrimaryIndigo.copy(alpha = 0.4f)
                    ),
                shape = RoundedCornerShape(24.dp),
                color = Color.White
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(PrimaryIndigo.copy(alpha = 0.1f), RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = PrimaryIndigo)
                    }
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = date,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Gray
                            )
                        )
                    }
                }
            }
        }

        @Composable
        private fun SectionHeader(title: String, icon: ImageVector) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = PrimaryIndigo,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                )
            }
        }

        @Composable
        private fun TripItemCard(item: TripItem, isHighlighted: Boolean = false) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = if (isHighlighted) 20.dp else 12.dp,
                        shape = RoundedCornerShape(24.dp),
                        spotColor = if (isHighlighted) PrimaryIndigo else PrimaryIndigo.copy(alpha = 0.3f)
                    ),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                border = if (isHighlighted) BorderStroke(2.dp, PrimaryIndigo.copy(alpha = 0.5f)) else null
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .height(IntrinsicSize.Min)
                ) {
                    // Image
                    Box {
                        if (item.imageResId != null) {
                            Image(
                                painter = painterResource(id = item.imageResId),
                                contentDescription = item.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(18.dp))
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(PrimaryIndigo.copy(alpha = 0.05f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = when(item.category) {
                                        "Place" -> Icons.Outlined.LocationOn
                                        "Restaurant" -> Icons.Outlined.Restaurant
                                        else -> Icons.Outlined.Diamond
                                    },
                                    contentDescription = null,
                                    tint = PrimaryIndigo.copy(alpha = 0.3f),
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }

                        if (isHighlighted) {
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .background(PrimaryIndigo, RoundedCornerShape(8.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                                    .align(Alignment.TopStart)
                            ) {
                                Text("GEM", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Spacer(Modifier.width(16.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (isHighlighted) PrimaryIndigo else Color.Black
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray.copy(alpha = 0.8f),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Rating",
                                tint = if (isHighlighted) GemGold else Color(0xFFFFB800),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = item.rating.toString(),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.DarkGray
                                )
                            )
                            Spacer(Modifier.weight(1f))
                            TextButton(
                                onClick = { /* Remove item */ },
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    "Remove",
                                    color = Color.Red.copy(alpha = 0.7f),
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }
        }

        @Composable
        private fun EmptyTripState() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Your trip is empty!",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Gray
                )
                Text(
                    "Explore and add your favorite places here.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray.copy(alpha = 0.6f)
                )
            }
        }

        /* --------------------------- PREVIEW --------------------------- */

        @Preview(showBackground = true)
        @Composable
        private fun MyTripPagePreview() {
            val samplePlaces = listOf(
                TripItem(1, "Eiffel Tower", "Iconic iron lattice tower on the Champ de Mars.", 4.8, "Place", R.drawable.ic_launcher_background),
                TripItem(2, "Louvre Museum", "The world's largest art museum and historic monument.", 4.7, "Place")
            )
            val sampleRestaurants = listOf(
                TripItem(3, "Le Meurice", "Elegant restaurant serving high-end French cuisine.", 4.9, "Restaurant")
            )
            val sampleGems = listOf(
                TripItem(4, "Hidden Courtyard", "A quiet, secret garden tucked away from the busy streets.", 4.6, "Hidden Gem")
            )

            MaterialTheme {
                MyTripPage(
                    selectedPlaces = samplePlaces,
                    restaurants = sampleRestaurants,
                    hiddenGems = sampleGems
                )
            }
        }
    }
}