package com.example.travelji.view.composables.profile_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelji.ui.theme.TravelJiTheme


@Composable
fun SimpleProfileScreen(
    name: String = "User Name",
    onLogout: () -> Unit = {}
) {
    // Defining the purple gradient (Dark to Light)
    val purpleGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF4A148C), // Dark Purple at top
            Color(0xFFF3E5F5)  // Very Light Purple at bottom
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(purpleGradient) // Applying the gradient here
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Pushes the card and button group towards the center
            Spacer(Modifier.weight(1f))

            // Profile Card containing Image, Name, and Description
            Card(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)), // Slightly translucent white
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 1. Profile Image (Taj Mahal)
                    Image(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.height(16.dp))

                    // 2. Name
                    Text(
                        text = name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    // 3. Description
                    Text(
                        text = "Travel Explorer",
                        fontSize = 16.sp,
                        color = Color(0xFF6A1B9A) // Matching purple theme
                    )
                }
            }

            // Fixed spacer to put logout just below the card
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onLogout() }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(android.R.drawable.ic_lock_power_off),
                    contentDescription = null,
                    tint = Color(0xFF4A148C) // Matching purple theme
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Logout",
                    fontSize = 18.sp,
                    color = Color(0xFF4A148C), // Matching purple theme
                    fontWeight = FontWeight.Medium
                )
            }

            // Pushes the group back up so it remains centered as a whole
            Spacer(Modifier.weight(1f))


        }
    }
}
@Preview(showBackground = true)
@Composable
fun SimpleProfilePreview() {
    TravelJiTheme {

//        SimpleProfilePreview()

    }

}