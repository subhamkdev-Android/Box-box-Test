package com.subhamkumar.boxboxapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.subhamkumar.boxboxapp.ui.details.RaceDetailScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }

        // race detail screen (new)
        composable("race_detail/{raceId}") { backStackEntry ->
            val raceId = backStackEntry.arguments?.getString("raceId")
            // Pass the raceId and navController to the detail screen
            RaceDetailScreen(navController = navController, raceId = raceId)
        }
    }
}

