package com.koolya.weathertestapp.data.datasource

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.koolya.weathertestapp.domain.datasource.LocationService
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationServiceImpl(private val locationService: FusedLocationProviderClient) :
    LocationService {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location =
        suspendCancellableCoroutine { continuation ->
            locationService.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                object : CancellationToken() {
                    override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                        return CancellationTokenSource().token
                    }

                    override fun isCancellationRequested(): Boolean = false

                }).addOnSuccessListener {
                if (it == null) {
                    continuation.resumeWithException(Exception("can't get geolocation, try again"))
                } else {
                    continuation.resume(it)
                }
            }
                .addOnFailureListener { continuation.resumeWithException(it) }
                .addOnCanceledListener { continuation.cancel(null) }
        }

}