package com.koolya.weathertestapp.data.api

import com.koolya.weathertestapp.BuildConfig
import com.koolya.weathertestapp.data.request.ExcludeRequest
import com.koolya.weathertestapp.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: ExcludeRequest = ExcludeRequest.CURRENT,
        @Query("appid") appid: String = BuildConfig.WEATHER_API_KEY,
    ): WeatherResponse
}