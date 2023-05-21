package com.koolya.weathertestapp.ui.state

sealed class WeatherState {

    object Initial: WeatherState()

    object Loading : WeatherState()

    object PermissionCheck : WeatherState()

    object PermissionNotGranted : WeatherState()

    object Info : WeatherState()

    data class Error(private val message: String) : WeatherState()

}
