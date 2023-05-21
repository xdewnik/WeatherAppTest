package com.koolya.weathertestapp.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.koolya.weathertestapp.ui.event.WeatherEvent
import com.koolya.weathertestapp.ui.state.WeatherState
import com.koolya.weathertestapp.ui.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel()) {
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )
    val uiState = viewModel.uiState.collectAsState(initial = WeatherState.Initial)

    LaunchedEffect(key1 = uiState.value is WeatherState.PermissionCheck) {
        locationPermissionsState.launchMultiplePermissionRequest()
    }
    LaunchedEffect(key1 = locationPermissionsState.permissions) {
        viewModel.sendEvent(WeatherEvent.PermissionResult(locationPermissionsState.allPermissionsGranted))
    }

}