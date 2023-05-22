package com.koolya.weathertestapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.koolya.weathertestapp.R
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
    LaunchedEffect(key1 = locationPermissionsState.revokedPermissions) {
        viewModel.sendEvent(WeatherEvent.PermissionResult(locationPermissionsState.allPermissionsGranted))
    }

    Box {
        when (val state = uiState.value) {
            is WeatherState.Error -> WeatherError(
                message = state.message,
                onRefresh = { viewModel.sendEvent(WeatherEvent.RefreshEvent) }
            )

            is WeatherState.Info -> WeatherInfo(
                state = state,
                onRefresh = { viewModel.sendEvent(WeatherEvent.RefreshEvent) }
            )

            WeatherState.Initial,
            WeatherState.PermissionCheck,
            WeatherState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            WeatherState.PermissionNotGranted -> Button(onClick = { viewModel.sendEvent(WeatherEvent.PermissionRequest) }) {
                Text(text = stringResource(id = R.string.button_please_give_permission))
            }
        }
    }
}