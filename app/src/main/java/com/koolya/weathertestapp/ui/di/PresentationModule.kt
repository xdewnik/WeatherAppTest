package com.koolya.weathertestapp.ui.di

import com.koolya.weathertestapp.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {
    viewModel { WeatherViewModel() }
}