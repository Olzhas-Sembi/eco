package com.example.eco.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "b2927c4413a66e44825620a2494b11d6"

interface WeatherApiService {

    @GET("geo/1.0/direct")
    suspend fun getCityCoordinates(
        @Query("q") cityName: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") apiKey: String = API_KEY
    ): List<CityResponse>

    @GET("data/2.5/air_pollution")
    suspend fun getAirQuality(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = API_KEY
    ): AirQualityResponse
}

// JSON-модель для геокодинга
data class CityResponse(
    val name: String,
    val lat: Double,
    val lon: Double
)

// JSON-модель для загрязненности воздуха
data class AirQualityResponse(
    val list: List<AirQualityData>
)

data class AirQualityData(
    val main: AirQualityIndex
)

data class AirQualityIndex(
    val aqi: Int
)

// Создаем Retrofit-клиент
object ApiClient {
    val retrofit: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}
