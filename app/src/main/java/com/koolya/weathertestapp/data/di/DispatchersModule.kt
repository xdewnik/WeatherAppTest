package com.koolya.weathertestapp.data.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val DispatchersModule = module {
    single(named(WeatherDispatchers.IO)) {
        Dispatchers.IO
    }
    single(named(WeatherDispatchers.MAIN)) {
        Dispatchers.Main
    }
    single(named(WeatherDispatchers.DEFAULT)) {
        Dispatchers.Default
    }
}

enum class WeatherDispatchers {
    IO,
    MAIN,
    DEFAULT
}