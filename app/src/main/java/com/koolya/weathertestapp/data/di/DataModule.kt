package com.koolya.weathertestapp.data.di

import com.koolya.weathertestapp.data.datasource.WeatherDataSourceImpl
import com.koolya.weathertestapp.domain.datasource.WeatherDataSource
import org.koin.dsl.module

val DataModule = module {
    factory<WeatherDataSource> { WeatherDataSourceImpl(get(), get()) }
}