package com.example.eco.domain.repository

import com.example.eco.City

interface CityRepository {
    suspend fun fetchCityData(cityNames: List<String>): List<City>
}
