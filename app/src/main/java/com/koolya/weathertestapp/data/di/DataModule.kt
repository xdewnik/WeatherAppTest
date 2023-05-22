package com.koolya.weathertestapp.data.di

import com.google.android.gms.location.LocationServices
import com.koolya.weathertestapp.data.datasource.LocationServiceImpl
import com.koolya.weathertestapp.data.datasource.WeatherDataSourceImpl
import com.koolya.weathertestapp.domain.datasource.LocationService
import com.koolya.weathertestapp.domain.datasource.WeatherDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val DataModule = module {

    single {
        LocationServices.getFusedLocationProviderClient(androidContext())
    }

    factory<LocationService> { LocationServiceImpl(get()) }

    factory<WeatherDataSource> {
        WeatherDataSourceImpl(
            get(),
            get(),
            get(named(WeatherDispatchers.IO))
        )
    }

}