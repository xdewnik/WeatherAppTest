package com.koolya.weathertestapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeatherError(
    modifier: Modifier = Modifier,
    message: String,
    onRefresh: () -> Unit,
) {
    Column(modifier = modifier) {

        Text(text = message, color = Color.Red)

        Spacer(modifier = Modifier.size(24.dp))

        RefreshButton(onRefresh = onRefresh)
    }
}