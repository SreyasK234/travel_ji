package com.example.travelji.view.composables.login_pages.login_screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelji.view.composables.login_pages.home_page.HomeScreen

@Composable
fun LoginAppNavigation(modifier: Modifier, authViewModel: AuthViewModel?, navFun: (String, String, String) -> Unit){
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
            LoginScreenModern (
                // onNavigateToSignUp = { navController.navigate("signup") }
                modifier,navController,authViewModel
            )
        }
        composable("signup") {
            SignUpScreenModern(
                //onBack = { navController.popBackStack() }
                modifier,navController,authViewModel
            )
        }
        composable("home") {
            val str = navController.previousBackStackEntry?.savedStateHandle?.get<String>("username") ?: ""
            HomeScreen(navFun, str)


        }
    }
}