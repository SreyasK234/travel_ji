package com.example.travelji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelji.ui.theme.TravelJiTheme

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var openingPageString = SCREENS.PLACES_SCREEN
        setContent {
            TravelJiTheme {
                MainView(openingPageString)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(openingPageString: SCREENS) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Travel Ji")},
                colors = TopAppBarColors(
                    containerColor = Color.Cyan,
                    scrolledContainerColor = Color.Cyan,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = Color.Cyan
            ) {

            }
        }
    ) { innerPadding ->
        MiddleView(startDestination = openingPageString,modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MiddleView(startDestination: SCREENS, modifier: Modifier) {

    val navController = rememberNavController()

    NavHost (
        startDestination = startDestination.screenName,
        navController = navController
    ) {
        composable (SCREENS.PLACES_SCREEN.screenName) {

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TravelJiTheme {
        MainView(SCREENS.PLACES_SCREEN)
    }
}