package com.example.travelji.view.composables.login_pages.login_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

fun Modifier.Companion.align(center: Alignment) {}

@Composable
fun SignUpScreenModern(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel?,
    isLoading: Boolean = false,
    onSignUpClick: (String, String) -> Unit = { _, _ -> },
    onBack: () -> Unit = {}
) {





    Box(

        modifier = modifier
            .fillMaxSize()
            .background(GradientIndigo)
            .padding(horizontal = 24.dp)
            .systemBarsPadding()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(32.dp),
                    spotColor = PrimaryIndigo.copy(alpha = 0.5f)
                ),
            shape = RoundedCornerShape(32.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.align(Alignment.TopStart)) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint =PrimaryIndigo
                        )
                    }
                }

                AppTitle()

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Join us and start exploring the world",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(32.dp))

                SignUpFormModern(
                    isLoading = isLoading,

                    authViewModel,
                    navController,

                    onSignUpClick = onSignUpClick
                )

                Spacer(Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Already have an account?",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextButton(onClick = onBack) {
                        Text(
                            "Sign in",
                            color = PrimaryIndigo,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
