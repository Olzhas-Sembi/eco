package com.example.eco.di

import com.example.eco.api.WeatherApiService
import com.example.eco.data.repository.CityRepositoryImpl
import com.example.eco.domain.repository.CityRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<WeatherApiService> {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
    single<CityRepository> { CityRepositoryImpl(get()) }
}