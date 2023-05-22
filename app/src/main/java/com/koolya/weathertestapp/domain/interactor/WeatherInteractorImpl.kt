package com.koolya.weathertestapp.domain.interactor

import com.koolya.weathertestapp.domain.datasource.WeatherDataSource
import com.koolya.weathertestapp.domain.model.Weather

class WeatherInteractorImpl(private val weatherDataSource: WeatherDataSource) : WeatherInteractor {
    override suspend fun getWeatherData(): Result<Weather> = kotlin.runCatching {
        weatherDataSource.getWeatherData()
    }

}