package com.example.eco.data.repository

import com.example.eco.City
import com.example.eco.api.WeatherApiService
import com.example.eco.domain.repository.CityRepository

class CityRepositoryImpl(
    private val api: WeatherApiService
) : CityRepository {

    override suspend fun fetchCityData(cityNames: List<String>): List<City> {
        val fetchedCities = mutableListOf<City>()
        for (city in cityNames) {
            try {
                val cityResponse = api.getCityCoordinates(city)
                if (cityResponse.isNotEmpty()) {
                    val lat = cityResponse[0].lat
                    val lon = cityResponse[0].lon
                    val airQualityResponse = api.getAirQuality(lat, lon)
                    val aqi = airQualityResponse.list.firstOrNull()?.main?.aqi ?: 0
                    fetchedCities.add(City(city, aqi))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return fetchedCities
    }
}
