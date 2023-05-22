package com.koolya.weathertestapp.data.mapper

import com.koolya.weathertestapp.BuildConfig
import com.koolya.weathertestapp.data.response.WeatherResponse
import com.koolya.weathertestapp.domain.model.Weather

fun WeatherResponse.toDomain(): Weather {
    val weatherStatus =
        requireNotNull(this.weatherStateResponses) { "weather status is null" }.run { this[0] }
    val icon = requireNotNull(weatherStatus.icon) { "icon is null" }

    return Weather(
        weatherImage = "${BuildConfig.BASE_URL}/img/wn/${icon}@2x.png",
        weatherStatus = requireNotNull(weatherStatus.main) { "weatherStatus.main is null" },
        temp = requireNotNull(this.characteristicsResponse?.temp?.toString()) { "weatherStatus.main is null" },
    )
}