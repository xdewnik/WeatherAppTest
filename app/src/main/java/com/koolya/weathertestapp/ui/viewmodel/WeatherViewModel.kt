package com.koolya.weathertestapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koolya.weathertestapp.domain.interactor.WeatherInteractor
import com.koolya.weathertestapp.ui.event.WeatherEvent
import com.koolya.weathertestapp.ui.state.WeatherState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val interactor: WeatherInteractor) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherState>(WeatherState.Initial)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<WeatherEvent>(replay = 1)

    init {
        viewModelScope.launch {
            _uiEvent.collect { handleEvent(it) }
        }
        sendEvent(WeatherEvent.PermissionRequest)
    }

    fun sendEvent(event: WeatherEvent) {
        _uiEvent.tryEmit(event)
    }

    private fun handleEvent(event: WeatherEvent) {
        when (event) {
            WeatherEvent.PermissionRequest -> _uiState.value = WeatherState.PermissionCheck
            is WeatherEvent.PermissionResult -> handlePermissionResult(event.isAllPermissionGranted)
            WeatherEvent.RefreshEvent -> getWeatherData()
        }
    }

    private fun handlePermissionResult(isAllPermissionGranted: Boolean) {
        if (isAllPermissionGranted) {
            sendEvent(WeatherEvent.RefreshEvent)
        } else {
            _uiState.value = WeatherState.PermissionNotGranted
        }
    }

    private fun getWeatherData() {
        _uiState.value = WeatherState.Loading
        viewModelScope.launch {
            interactor.getWeatherData()
                .onSuccess {
                    _uiState.value = WeatherState.Info(
                        temp = it.temp,
                        icon = it.weatherImage,
                        description = it.weatherStatus,
                    )
                }
                .onFailure { _uiState.value = WeatherState.Error(it.message.orEmpty()) }
        }
    }
}