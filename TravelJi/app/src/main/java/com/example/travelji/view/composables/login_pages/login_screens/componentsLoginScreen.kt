package com.example.travelji.view.composables.login_pages.login_screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AppTitle() {
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
fun LoginFormModern(
    isLoading: Boolean,
    navController: NavController,
    authViewModel: AuthViewModel?,
    emailInitial: String,
    onLoginClick: (String, String) -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val authState = authViewModel?.authState?.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState?.value) {
        when(authState?.value){
            is AuthState.Authenticated -> {
                navController.currentBackStackEntry?.savedStateHandle?.set("username", email)
                navController.navigate("home")
            }
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }


    //var email by rememberSaveable { mutableStateOf(emailInitial) }
    //var password by rememberSaveable { mutableStateOf("") }
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
            //

            authViewModel?.login(email,password)
            if (!isLoading && validate()) onLoginClick(email.trim(), password)
        },
        enabled = authState?.value != AuthState.Loading,
        //enabled = !isLoading,
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
            Text("Login In", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun SignUpFormModern(
    isLoading: Boolean,
    authViewModel: AuthViewModel?,
    navController: NavController,
    onSignUpClick: (String, String) -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val authState = authViewModel?.authState?.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState?.value) {
        when (authState?.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }


    //var email by rememberSaveable { mutableStateOf("") }
    //var password by rememberSaveable { mutableStateOf("") }
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
//        confirmPasswordError = when {
//            confirmPassword != password -> { ok = false; "Passwords do not match" }
//            else -> null
//        }
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

//    PasswordFieldModern(
//        value = confirmPassword,
//        onValueChange = {
//            confirmPassword = it
//            if (confirmPasswordError != null) confirmPasswordError = null
//        },
//        isError = confirmPasswordError != null,
//        errorText = confirmPasswordError,
//        label = "Confirm password",
//        onDone = {
//            if (!isLoading && validate()) onSignUpClick(email.trim(), password)
//        }
//    )

    Spacer(Modifier.height(32.dp))

    Button(
        onClick = {
            //

            authViewModel?.signup(email, password)
            if (!isLoading && validate()) onSignUpClick(email.trim(), password)
        },
        enabled = authState?.value != AuthState.Loading,

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