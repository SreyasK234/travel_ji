package com.example.travelji

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.travelji.ui.theme.TravelJiTheme
import com.example.travelji.view.composables.login_pages.login_screens.AuthViewModel
import com.example.travelji.view.composables.login_pages.login_screens.LoginAppNavigation

class MainActivity : ComponentActivity() {

    companion object{
        var authViewModel : AuthViewModel? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        authViewModel = AuthViewModel()
        val navFun : (String, String, String) -> Unit = {openingString, cityName, username ->
            val intent = Intent(this@MainActivity, AppActivity::class.java)
            intent.putExtra("openingString", openingString)
            intent.putExtra("cityName", cityName)
            intent.putExtra("username", username)
            startActivity(intent)
        }
        setContent {
            TravelJiTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginAppNavigation(Modifier.padding(innerPadding),authViewModel, navFun )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TravelJiTheme {
        Greeting("Android")
    }
}