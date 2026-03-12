package com.example.travelji.view.composables

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelji.view.composables.SplashScreen.Companion.SplashScreen


class LoginPage {
    companion object{


        /* --------------------------- THEME --------------------------- */

        private val PrimaryIndigo = Color(0xFF4F46E5)
        private val SecondaryIndigo = Color(0xFF6366F1)
        private val SoftIndigoBg = Color(0xFFF8FAFC)
        private val GradientIndigo = Brush.verticalGradient(
            listOf(Color(0xFFEEF2FF), Color(0xFFE0E7FF))
        )

        /* --------------------------- NAVIGATION --------------------------- */

        @Composable
        fun LoginAppNavigation() {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen(onTimeout = {
                        navController.navigate("login") {
                            popUpTo("splash") { inclusive = true }
                        }
                    })
                }
                composable("login") {
                    LoginScreenModern(
                        onNavigateToSignUp = { navController.navigate("signup") }
                    )
                }
                composable("signup") {
                    SignUpScreenModern(
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }

        /* --------------------------- SCREENS --------------------------- */

        @Composable
        fun LoginScreenModern(
            modifier: Modifier = Modifier,
            isLoading: Boolean = false,
            emailInitial: String = "",
            onLoginClick: (email: String, password: String) -> Unit = { _, _ -> },
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
                            TextButton(onClick = onNavigateToSignUp) {
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

        @Composable
        fun SignUpScreenModern(
            modifier: Modifier = Modifier,
            isLoading: Boolean = false,
            onSignUpClick: (email: String, password: String) -> Unit = { _, _ -> },
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
                            IconButton(onClick = onBack, modifier = Modifier.align(Alignment.TopStart)) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = PrimaryIndigo
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

        /* --------------------------- COMPONENTS --------------------------- */

        @Composable
        private fun AppTitle() {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Travel Guide",
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
        private fun LoginFormModern(
            isLoading: Boolean,
            emailInitial: String,
            onLoginClick: (email: String, password: String) -> Unit
        ) {
            var email by rememberSaveable { mutableStateOf(emailInitial) }
            var password by rememberSaveable { mutableStateOf("") }
            var emailError by rememberSaveable { mutableStateOf<String?>(null) }
            var passwordError by rememberSaveable { mutableStateOf<String?>(null) }

            fun validate(): Boolean {
                var ok = true
                val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
                emailError = when {
                    email.isBlank() -> { ok = false; "Email is required" }
                    !emailRegex.matches(email) -> { ok = false; "Enter a valid email address" }
                    else -> null
                }
                passwordError = when {
                    password.isBlank() -> { ok = false; "Password is required" }
                    password.length <= 10 -> { ok = false; "Must be > 10 characters" }
                    else -> null
                }
                return ok
            }

            EmailFieldModern(
                value = email,
                onValueChange = {
                    email = it
                    if (emailError != null) emailError = null
                },
                isError = emailError != null,
                errorText = emailError
            )

            Spacer(Modifier.height(16.dp))

            PasswordFieldModern(
                value = password,
                onValueChange = {
                    password = it
                    if (passwordError != null) passwordError = null
                },
                isError = passwordError != null,
                errorText = passwordError,
                label = "Password",
                onDone = {
                    if (!isLoading && validate()) onLoginClick(email.trim(), password)
                }
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    if (!isLoading && validate()) onLoginClick(email.trim(), password)
                },
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(12.dp, RoundedCornerShape(16.dp), spotColor = PrimaryIndigo),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryIndigo,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Sign In", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

        @Composable
        private fun SignUpFormModern(
            isLoading: Boolean,
            onSignUpClick: (email: String, password: String) -> Unit
        ) {
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            var confirmPassword by rememberSaveable { mutableStateOf("") }
            var emailError by rememberSaveable { mutableStateOf<String?>(null) }
            var passwordError by rememberSaveable { mutableStateOf<String?>(null) }
            var confirmPasswordError by rememberSaveable { mutableStateOf<String?>(null) }

            fun validate(): Boolean {
                var ok = true
                val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
                emailError = when {
                    email.isBlank() -> { ok = false; "Email is required" }
                    !emailRegex.matches(email) -> { ok = false; "Enter a valid email address" }
                    else -> null
                }
                passwordError = when {
                    password.isBlank() -> { ok = false; "Password is required" }
                    password.length <= 10 -> { ok = false; "Must be > 10 characters" }
                    else -> null
                }
                confirmPasswordError = when {
                    confirmPassword != password -> { ok = false; "Passwords do not match" }
                    else -> null
                }
                return ok
            }

            EmailFieldModern(
                value = email,
                onValueChange = {
                    email = it
                    if (emailError != null) emailError = null
                },
                isError = emailError != null,
                errorText = emailError
            )

            Spacer(Modifier.height(16.dp))

            PasswordFieldModern(
                value = password,
                onValueChange = {
                    password = it
                    if (passwordError != null) passwordError = null
                },
                isError = passwordError != null,
                errorText = passwordError,
                label = "Create password"
            )

            Spacer(Modifier.height(16.dp))

            PasswordFieldModern(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    if (confirmPasswordError != null) confirmPasswordError = null
                },
                isError = confirmPasswordError != null,
                errorText = confirmPasswordError,
                label = "Confirm password",
                onDone = {
                    if (!isLoading && validate()) onSignUpClick(email.trim(), password)
                }
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    if (!isLoading && validate()) onSignUpClick(email.trim(), password)
                },
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(12.dp, RoundedCornerShape(16.dp), spotColor = PrimaryIndigo),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryIndigo,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Create Account", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }

        @Composable
        private fun EmailFieldModern(
            value: String,
            onValueChange: (String) -> Unit,
            isError: Boolean,
            errorText: String?
        ) {
            Column {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null, tint = PrimaryIndigo) },
                    label = { Text("Email Address") },
                    singleLine = true,
                    isError = isError,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryIndigo,
                        unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                        focusedLabelColor = PrimaryIndigo,
                        cursorColor = PrimaryIndigo
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                AnimatedVisibility(visible = isError && !errorText.isNullOrBlank()) {
                    Text(
                        text = errorText.orEmpty(),
                        color = Color.Red,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }
            }
        }

        @Composable
        private fun PasswordFieldModern(
            value: String,
            onValueChange: (String) -> Unit,
            isError: Boolean,
            errorText: String?,
            label: String = "Password",
            onDone: () -> Unit = {}
        ) {
            var visible by rememberSaveable { mutableStateOf(false) }
            val isTooShort = value.isNotEmpty() && value.length <= 10
            val effectiveIsError = isError || isTooShort
            val effectiveErrorText = if (isTooShort) "Password must be greater than 10 characters" else errorText

            Column {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null, tint = PrimaryIndigo) },
                    trailingIcon = {
                        IconButton(onClick = { visible = !visible }) {
                            Icon(
                                imageVector = if (visible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = "Toggle password",
                                tint = PrimaryIndigo
                            )
                        }
                    },
                    label = { Text(label) },
                    singleLine = true,
                    isError = effectiveIsError,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryIndigo,
                        unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                        focusedLabelColor = PrimaryIndigo,
                        cursorColor = PrimaryIndigo,
                        errorBorderColor = Color.Red
                    ),
                    visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { onDone() }),
                    modifier = Modifier.fillMaxWidth()
                )
                AnimatedVisibility(visible = effectiveIsError && !effectiveErrorText.isNullOrBlank()) {
                    Text(
                        text = effectiveErrorText.orEmpty(),
                        color = Color.Red,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }
            }
        }

        /* --------------------------- PREVIEWS --------------------------- */

        @Preview(name = "Login – Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
        @Composable
        private fun Preview() {
            MaterialTheme {
                LoginAppNavigation()
            }
        }

    }


}