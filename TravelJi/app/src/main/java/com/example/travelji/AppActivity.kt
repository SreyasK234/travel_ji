package com.example.travelji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelji.model.CardItemPojo
import com.example.travelji.ui.theme.TravelJiTheme
import com.example.travelji.view.composables.app_pages.FoodListView
import com.example.travelji.view.composables.app_pages.HiddenGemsListView
import com.example.travelji.view.composables.app_pages.PlacesListView
import com.example.travelji.view.composables.mytrip_page.MyTripPage
import com.example.travelji.view.composables.profile_page.SimpleProfileScreen
import com.example.travelji.viewmodel.AppViewModel

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var openingPageString = SCREENS.PLACES_SCREEN
        val appViewModel = AppViewModel()
        setContent {
            TravelJiTheme {
//                var data : List<CardItemPojo> by rememberSaveable { mutableStateOf(emptyList())}
//                var dataFood : List<CardItemPojo> by rememberSaveable { mutableStateOf(emptyList())}

                MainView(openingPageString, appViewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(openingPageString: SCREENS, appViewModel: AppViewModel) {

    var pageString by rememberSaveable { mutableStateOf(openingPageString) }

    val data by appViewModel.dataPlaces.collectAsState()
    val dataFood by appViewModel.dataFoodPlaces.collectAsState()
    val dataHiddenGems by appViewModel.dataHiddenGems.collectAsState()


    LaunchedEffect(Unit) {
        appViewModel.loadPlaces()
        appViewModel.loadFoodPlaces()
        appViewModel.loadHiddenGems()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(100.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Travel Ji",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        IconButton(onClick = {pageString = SCREENS.PROFILE_SCREEN}) {
                            Icon(Icons.Default.AccountCircle, null, tint = Color.Black, modifier = Modifier.size(28.dp))
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6A1B9A) // Dark Purple
                )
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                // Glassmorphism Floating Bottom Bar
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.92f)
                        .height(72.dp),
                    shape = RoundedCornerShape(36.dp),
                    color = Color.Black.copy(alpha = 0.15f), // Glassy background
                    border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.3f)),
                    tonalElevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {pageString = SCREENS.PLACES_SCREEN}) {
                            Icon(Icons.Default.Place, null, tint = Color.Black, modifier = Modifier.size(28.dp))
                        }
                        IconButton(onClick = {pageString = SCREENS.FOOD_SCREEN}) {
                            Icon(Icons.Default.Restaurant, null, tint = Color.Black, modifier = Modifier.size(28.dp))
                        }
                        IconButton(onClick = {pageString = SCREENS.MY_TRIP}) {
                            Icon(Icons.Default.Explore, null, tint = Color.Black, modifier = Modifier.size(28.dp))
                        }
                        IconButton(onClick = {pageString = SCREENS.HIDDEN_GEMS}) {
                            Icon(Icons.Default.Backpack, null, tint = Color.Black, modifier = Modifier.size(28.dp))
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        MiddleView(startDestination = pageString,modifier = Modifier.padding(innerPadding), data, dataFood, dataHiddenGems)
    }
}

@Composable
fun MiddleView(
    startDestination: SCREENS,
    modifier: Modifier,
    data: List<CardItemPojo>,
    dataFood: List<CardItemPojo>,
    dataHiddenGems: List<CardItemPojo>
) {

    val navController = rememberNavController()

    NavHost (
        startDestination = startDestination.screenName,
        navController = navController
    ) {
        composable (SCREENS.PLACES_SCREEN.screenName) {
            PlacesListView(modifier, data)
        }
        composable (SCREENS.FOOD_SCREEN.screenName) {
            FoodListView(modifier, dataFood)
        }
        composable (SCREENS.MY_TRIP.screenName) {
            MyTripPage()
        }
        composable(SCREENS.HIDDEN_GEMS.screenName) {
            HiddenGemsListView(modifier, dataHiddenGems)
        }
        composable (SCREENS.PROFILE_SCREEN.screenName) {
            SimpleProfileScreen()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TravelJiTheme {
//        MainView(SCREENS.PLACES_SCREEN, data)
    }
}