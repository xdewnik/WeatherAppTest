package com.koolya.weathertestapp.domain.interactor

import com.koolya.weathertestapp.domain.model.Weather

interface WeatherInteractor {

    suspend fun getWeatherData(): Result<Weather>

}