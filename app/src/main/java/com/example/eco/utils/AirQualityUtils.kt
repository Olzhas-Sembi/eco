package com.example.eco

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun getAirQualityAnimation(aqi: Int): Int {
    return when {
        aqi < 50 -> R.raw.good
        aqi < 100 -> R.raw.warning
        else -> R.raw.bad
    }
}

fun getAirQualityDescription(aqi: Int): String {
    return when {
        aqi < 50 -> "Воздух чистый и безопасный для дыхания."
        aqi < 100 -> "Качество воздуха умеренное, может быть неприятным для чувствительных людей."
        else -> "Загрязненный воздух. Рекомендуется избегать длительного пребывания на улице."
    }
}

fun getBackgroundGradient(aqi: Int): Brush {
    return when {
        aqi < 50 -> Brush.verticalGradient(listOf(Color(0xFFAEFCC9), Color(0xFF74CCA4)))
        aqi < 100 -> Brush.verticalGradient(listOf(Color(0xFFFFDA58), Color(0xFFFFB64C)))
        else -> Brush.verticalGradient(listOf(Color(0xFFFF9090), Color(0xFFFF5151)))
    }
}
