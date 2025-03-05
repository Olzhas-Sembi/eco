package com.example.eco.widget

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.appwidget.*
import androidx.glance.layout.*
import androidx.glance.text.*
import androidx.glance.unit.ColorProvider
import kotlin.random.Random

class EcoAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val cities = listOf(
            "Алматы" to 135,
            "Астана" to 60,
            "Шымкент" to 22,
            "Костанай" to 10,
            "Актобе" to 30
        )

        val randomCity = cities[Random.nextInt(cities.size)]
        val cityName = randomCity.first
        val aqi = randomCity.second

        val aqiColor = when {
            aqi <= 50 -> Color(0xFF4CAF50)
            aqi <= 100 -> Color(0xFFFFEB3B)
            else -> Color(0xFFFF5722)
        }

        provideContent {
            GlanceTheme {
                Box(
                    modifier = GlanceModifier.fillMaxSize()
                        .background(ColorProvider(Color(0xFFBBDEFB)))
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = GlanceModifier.fillMaxWidth()
                            .background(ColorProvider(Color.White))
                            .padding(16.dp)
                            .cornerRadius(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = cityName,
                            style = TextStyle(
                                color = ColorProvider(Color.Black),
                                fontSize = 18.sp
                            )
                        )
                        Spacer(modifier = GlanceModifier.height(8.dp))
                        Text(
                            text = "AQI: $aqi",
                            style = TextStyle(
                                color = ColorProvider(aqiColor),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}
