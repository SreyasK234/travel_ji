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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreenModern(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel?,
    isLoading: Boolean = false,
    emailInitial: String = "",
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onNavigateToSignUp: () -> Unit = {}
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
                AppTitle()

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Sign in to continue your journey",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(40.dp))

                LoginFormModern(
                    isLoading = isLoading,
                    navController,
                    authViewModel,
                    emailInitial = emailInitial,
                    onLoginClick = onLoginClick
                )

                Spacer(Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "New here?",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextButton(onClick = { navController.navigate("signup") }) {
                        Text(
                            "Create account",
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
@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun TravelLoginScreenPreview() {
    MaterialTheme {
        //LoginScreenModern()
    }
}