package com.koolya.weathertestapp.ui.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.RefreshButton(onRefresh: () -> Unit) {
    IconButton(onClick = onRefresh, modifier = Modifier.align(Alignment.Start)) {
        Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh")
    }
}