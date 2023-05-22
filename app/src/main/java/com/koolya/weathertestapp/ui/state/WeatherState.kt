package com.koolya.weathertestapp.ui.state

sealed class WeatherState {

    object Initial : WeatherState()

    object Loading : WeatherState()

    object PermissionCheck : WeatherState()

    object PermissionNotGranted : WeatherState()

    data class Info(val temp: String, val icon: String, val description: String) : WeatherState()

    data class Error(val message: String) : WeatherState()

}
