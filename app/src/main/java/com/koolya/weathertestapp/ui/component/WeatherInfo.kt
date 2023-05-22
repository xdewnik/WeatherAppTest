package com.koolya.weathertestapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.koolya.weathertestapp.ui.state.WeatherState

@Composable
fun WeatherInfo(
    modifier: Modifier = Modifier,
    state: WeatherState.Info,
    onRefresh: () -> Unit,
) {
    Column(modifier = modifier) {
        RefreshButton(onRefresh)

        Spacer(modifier = Modifier.size(24.dp))

        AsyncImage(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .aspectRatio(1f),
            model = state.icon,
            contentDescription = "icon"
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(text = state.description)

        Spacer(modifier = Modifier.size(24.dp))

        Text(text = state.temp)
    }
}