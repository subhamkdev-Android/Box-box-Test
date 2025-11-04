package com.subhamkumar.boxboxapp.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.subhamkumar.boxboxapp.ui.details.DriverDetailsScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("driver_details/{driverId}") { backStackEntry ->
            val driverId = backStackEntry.arguments?.getString("driverId")
            DriverDetailsScreen(driverId = driverId)
        }
    }
}
