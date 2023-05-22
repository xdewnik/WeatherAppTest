package com.koolya.weathertestapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.koolya.weathertestapp.R
import com.koolya.weathertestapp.ui.state.WeatherState

@Composable
fun WeatherInfo(
    modifier: Modifier = Modifier,
    state: WeatherState.Info,
    onRefresh: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        RefreshButton(onRefresh)

        Spacer(modifier = Modifier.size(24.dp))

        AsyncImage(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .aspectRatio(1f)
                .align(CenterHorizontally),
            model = state.icon,
            contentDescription = "icon"
        )

        Spacer(modifier = Modifier.size(24.dp))

        TextWithDescription(
            description = R.string.weather_info_description_status,
            text = state.description
        )

        Spacer(modifier = Modifier.size(24.dp))

        TextWithDescription(description = R.string.weather_info_description_temp, text = state.temp)

    }
}

@Composable
fun ColumnScope.TextWithDescription(
    modifier: Modifier = Modifier,
    @StringRes description: Int, text: String
) {
    Row(modifier = modifier) {
        Text(text = stringResource(id = description))

        Spacer(modifier = Modifier.size(16.dp))

        Text(text = text)
    }

}