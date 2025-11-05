package com.subhamkumar.boxboxapp

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
import com.subhamkumar.boxboxapp.ui.theme.BoxBoxAppTheme
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.subhamkumar.boxboxapp.di.appModule
import com.subhamkumar.boxboxapp.ui.home.AppNavGraph
import com.subhamkumar.boxboxapp.ui.home.HomeScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppNavGraph(navController)
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
    BoxBoxAppTheme {
        Greeting("Android")
    }
}