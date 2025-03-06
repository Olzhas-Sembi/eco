package com.example.eco.widget

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.*
import androidx.glance.appwidget.*
import androidx.glance.layout.*
import androidx.glance.text.*
import androidx.glance.unit.ColorProvider
import com.example.eco.api.ApiClient
import kotlinx.coroutines.*

class EcoAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val cityName = "Алматы"  // Можно изменить или сделать динамическим
        var aqi: Int? = null

        withContext(Dispatchers.IO) {
            try {
                val cityData = ApiClient.retrofit.getCityCoordinates(cityName)
                if (cityData.isNotEmpty()) {
                    val lat = cityData[0].lat
                    val lon = cityData[0].lon

                    val airQualityData = ApiClient.retrofit.getAirQuality(lat, lon)
                    aqi = airQualityData.list.firstOrNull()?.main?.aqi
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        provideContent {
            GlanceTheme {
                Column(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(ColorProvider(Color.White)) // Фон белый
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Город: $cityName",
                        style = TextStyle(color = ColorProvider(Color.Black))
                    )
                    Text(
                        text = "AQI: ${aqi ?: "Ошибка"}",
                        style = TextStyle(
                            color = ColorProvider(
                                when (aqi) {
                                    1 -> Color(0xFF00E400) // Зеленый (очень хороший)
                                    2 -> Color(0xFFFFFF00) // Желтый (хороший)
                                    3 -> Color(0xFFFFA500) // Оранжевый (умеренный)
                                    4 -> Color(0xFFFF0000) // Красный (вредный)
                                    5 -> Color(0xFF7E0023) // Бордовый (очень вредный)
                                    else -> Color(0xFF000000) // Черный (ошибка)
                                }
                            )
                        )
                    )
                }
            }
        }
    }
}
