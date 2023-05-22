package com.koolya.weathertestapp.domain.di

import com.koolya.weathertestapp.domain.interactor.WeatherInteractor
import com.koolya.weathertestapp.domain.interactor.WeatherInteractorImpl
import org.koin.dsl.module

val DomainModule = module {
    factory<WeatherInteractor> { WeatherInteractorImpl(get()) }
}