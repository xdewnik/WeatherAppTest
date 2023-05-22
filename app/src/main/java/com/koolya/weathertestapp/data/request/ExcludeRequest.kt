package com.koolya.weathertestapp.data.request

import com.google.gson.annotations.SerializedName

enum class ExcludeRequest {

    @SerializedName("current")
    CURRENT,

    @SerializedName("minutely")
    MINUTELY,

    @SerializedName("hourly")
    HOURLY,

    @SerializedName("daily")
    DAILY,

    @SerializedName("alerts")
    ALERTS,
}