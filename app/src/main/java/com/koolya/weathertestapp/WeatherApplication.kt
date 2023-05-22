package com.koolya.weathertestapp

import android.app.Application
import com.google.android.gms.location.LocationServices
import com.koolya.weathertestapp.data.di.ApiModule
import com.koolya.weathertestapp.data.di.DataModule
import com.koolya.weathertestapp.data.di.DispatchersModule
import com.koolya.weathertestapp.domain.di.DomainModule
import com.koolya.weathertestapp.ui.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(
                ApiModule,
                PresentationModule,
                DispatchersModule,
                DataModule,
                DomainModule,
            )
        }
    }
}