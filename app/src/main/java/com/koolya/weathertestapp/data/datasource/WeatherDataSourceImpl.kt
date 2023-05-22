package com.koolya.weathertestapp.data.datasource

import com.koolya.weathertestapp.data.api.WeatherApi
import com.koolya.weathertestapp.data.mapper.toDomain
import com.koolya.weathertestapp.domain.datasource.LocationService
import com.koolya.weathertestapp.domain.datasource.WeatherDataSource
import com.koolya.weathertestapp.domain.model.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class WeatherDataSourceImpl(
    private val weatherApi: WeatherApi,
    private val locationService: LocationService,
    private val ioDispatcher: CoroutineDispatcher,
) : WeatherDataSource {

    override suspend fun getWeatherData(): Weather = withContext(ioDispatcher) {
        val location = locationService.getCurrentLocation()
        weatherApi.getWeatherData(lat = location.latitude, lon = location.longitude).toDomain()
    }

}