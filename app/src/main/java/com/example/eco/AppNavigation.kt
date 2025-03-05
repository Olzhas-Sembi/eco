package com.example.eco.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eco.ui.screens.CityListScreen
import com.example.eco.ui.screens.CityDetailsScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "city_list") {
        composable("city_list") { CityListScreen(navController) }
        composable("city_details/{cityName}/{aqi}") { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            val aqi = backStackEntry.arguments?.getString("aqi")?.toIntOrNull() ?: 0
            CityDetailsScreen(cityName, aqi, navController)
        }
    }
}
