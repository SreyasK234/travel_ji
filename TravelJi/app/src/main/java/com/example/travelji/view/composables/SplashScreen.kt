package com.example.travelji.view.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
class SplashScreen {
    companion object{

        private val PrimaryIndigo = Color(0xFF4F46E5)
        private val SecondaryIndigo = Color(0xFF6366F1)
        private val AccentOrange = Color(0xFFF59E0B)

        // Gradient matching the Login Page
        private val GradientIndigo = Brush.verticalGradient(
            listOf(Color(0xFFEEF2FF), Color(0xFFE0E7FF))
        )

        @Composable
        fun SplashScreen(onTimeout: () -> Unit) {
            var visible by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                visible = true
                delay(2000)
                onTimeout()
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GradientIndigo)
                    .systemBarsPadding(),
                contentAlignment = Alignment.Center
            ) {
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(animationSpec = tween(1000)) + scaleIn(animationSpec = tween(1000))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Logo in the top half
                        Spacer(modifier = Modifier.weight(0.4f))

                        // Compass Logo - Reduced size and subtle background
                        Box(
                            modifier = Modifier
                                .size(110.dp) // Reduced from 140.dp
                                .clip(CircleShape)
                                .background(PrimaryIndigo.copy(alpha = 0.08f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Explore,
                                contentDescription = "Explore Logo",
                                tint = PrimaryIndigo,
                                modifier = Modifier.size(60.dp) // Reduced from 80.dp
                            )
                            // Small accent "North" dot
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .offset(y = (-24).dp)
                                    .clip(CircleShape)
                                    .background(AccentOrange)
                            )
                        }

                        Spacer(Modifier.height(32.dp))

                        // App Name with Indigo colors to match login theme
                        Text(
                            text = "Travel Guide",
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = PrimaryIndigo,
                                letterSpacing = (-1).sp,
                                fontFamily = FontFamily.SansSerif
                            ),
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.height(12.dp))

                        // Tagline with secondary indigo
                        Text(
                            text = "Explore. Plan. Experience.",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = SecondaryIndigo.copy(alpha = 0.8f),
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            ),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.weight(0.6f))

                        // Subtle indicator at the bottom
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(4.dp)
                                .clip(CircleShape)
                                .background(PrimaryIndigo.copy(alpha = 0.2f))
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                    }
                }
            }
        }

    }
}