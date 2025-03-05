package com.example.eco

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eco.ui.components.AirQualityAnimation

@Composable
fun CityCard(city: City, navController: NavController) {
    var isVisible by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.9f,
        animationSpec = tween(durationMillis = 500)
    )

    LaunchedEffect(Unit) {
        isVisible = true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .scale(scale)
            .clickable { navController.navigate("city_details/${city.name}/${city.aqi}") },
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val animationRes = getAirQualityAnimation(city.aqi)
            AirQualityAnimation(animationRes)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "${city.name}: ${city.aqi} AQI")
        }
    }
}
