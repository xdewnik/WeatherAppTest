package com.koolya.weathertestapp.ui.event

sealed class WeatherEvent {

    data class PermissionResult(val isAllPermissionGranted: Boolean) : WeatherEvent()

    object PermissionRequest : WeatherEvent()

    object RefreshEvent : WeatherEvent()
}
