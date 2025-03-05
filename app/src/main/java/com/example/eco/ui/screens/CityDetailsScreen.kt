package com.example.eco.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eco.getAirQualityAnimation
import com.example.eco.getAirQualityDescription
import com.example.eco.getBackgroundGradient
import com.example.eco.ui.components.AirQualityAnimation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailsScreen(cityName: String, aqi: Int, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(cityName) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                }
            }
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(getBackgroundGradient(aqi))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val animationRes = getAirQualityAnimation(aqi)
                AirQualityAnimation(animationRes)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "$cityName: $aqi AQI", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = getAirQualityDescription(aqi), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

