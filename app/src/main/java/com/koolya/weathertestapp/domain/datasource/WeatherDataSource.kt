package com.koolya.weathertestapp.domain.datasource

import com.koolya.weathertestapp.domain.model.Weather

interface WeatherDataSource {

    suspend fun getWeatherData(): Weather

}