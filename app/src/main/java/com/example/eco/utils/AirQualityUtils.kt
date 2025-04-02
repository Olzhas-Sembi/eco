package com.example.eco.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.font.FontWeight
import com.example.eco.R

val googleFontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

fun getAirQualityAnimation(aqi: Int): Int {
    return when (aqi) {
        1 -> R.raw.good
        2 -> R.raw.good
        3 -> R.raw.warning
        4 -> R.raw.warning
        5 -> R.raw.bad
        else -> R.raw.bad
    }
}

fun getAirQualityDescription(aqi: Int): String {
    return when (aqi) {
        1 -> "Воздух чистый и безопасный для дыхания."
        2 -> "Качество воздуха хорошее, но может быть неприятным для особо чувствительных людей."
        3 -> "Умеренное загрязнение, стоит ограничить длительные прогулки."
        4 -> "Загрязнение высокое, рекомендуется носить маску на улице."
        5 -> "Опасный уровень загрязнения! Избегайте выхода на улицу."
        else -> "Нет данных о качестве воздуха."
    }
}

fun getBackgroundGradient(aqi: Int): Brush {
    return when (aqi) {
        1 -> Brush.verticalGradient(listOf(Color(0xFFDFFFE3), Color(0xFFB6F3C8))) // Светло-зеленый
        2 -> Brush.verticalGradient(listOf(Color(0xFFFFF3C4), Color(0xFFFFDD80))) // Светло-желтый
        3 -> Brush.verticalGradient(listOf(Color(0xFFFFE0B2), Color(0xFFFFB74D))) // Светло-оранжевый
        4 -> Brush.verticalGradient(listOf(Color(0xFFFFC0C0), Color(0xFFFF8080))) // Светло-красный
        5 -> Brush.verticalGradient(listOf(Color(0xFFFFAFAF), Color(0xFFFF7070))) // Светло-бордовый
        else -> Brush.verticalGradient(listOf(Color(0xFFE0E0E0), Color(0xFFB0B0B0))) // Серый (нет данных)
    }
}

val customFontFamily = FontFamily(
    Font(GoogleFont("Roboto"), googleFontProvider, FontWeight.Bold),
    Font(GoogleFont("Roboto"), googleFontProvider, FontWeight.Medium),
    Font(GoogleFont("Roboto"), googleFontProvider, FontWeight.Normal)
)
