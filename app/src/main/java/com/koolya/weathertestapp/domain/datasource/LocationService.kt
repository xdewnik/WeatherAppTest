package com.koolya.weathertestapp.domain.datasource

import android.location.Location

interface LocationService {

    suspend fun getCurrentLocation(): Location

}