package com.example.travelji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelji.db.RemoteDBHelper
import com.example.travelji.model.CardItemPojo
import com.example.travelji.ui.theme.MyColor
import com.example.travelji.ui.theme.TravelJiTheme
import com.example.travelji.view.composables.app_pages.FoodListView
import com.example.travelji.view.composables.app_pages.PlacesListView
import kotlinx.coroutines.launch

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var openingPageString = SCREENS.PLACES_SCREEN
        setContent {
            TravelJiTheme {
                var data : List<CardItemPojo> by rememberSaveable { mutableStateOf(emptyList())}
                var dataFood : List<CardItemPojo> by rememberSaveable { mutableStateOf(emptyList())}

                LaunchedEffect(Unit) {
                    lifecycleScope.launch {
                        data = RemoteDBHelper.getCityCategory("Hyderabad", "recommendedPlaces")
                    }
                    lifecycleScope.launch {
                        dataFood = RemoteDBHelper.getCityCategory("Hyderabad", "recommendedRestaurants")
                    }
                }
                MainView(openingPageString, data, dataFood)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(openingPageString: SCREENS, data: List<CardItemPojo>, dataFood: List<CardItemPojo>) {

    var pageString by rememberSaveable { mutableStateOf(openingPageString) }

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
                containerColor = MyColor
            ) {
                Row  (horizontalArrangement = Arrangement.SpaceEvenly) {
                    IconButton(onClick = {pageString = SCREENS.PLACES_SCREEN}) { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")  }
                    IconButton(onClick = {pageString = SCREENS.FOOD_SCREEN}) { Icon(imageVector = Icons.Default.Search, contentDescription = "")  }
                }
            }
        }
    ) { innerPadding ->
        MiddleView(startDestination = pageString,modifier = Modifier.padding(innerPadding), data, dataFood)
    }
}

@Composable
fun MiddleView(startDestination: SCREENS, modifier: Modifier, data: List<CardItemPojo>, dataFood: List<CardItemPojo>) {

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
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TravelJiTheme {
//        MainView(SCREENS.PLACES_SCREEN, data)
    }
}