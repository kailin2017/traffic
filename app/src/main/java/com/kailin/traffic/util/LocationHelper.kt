package com.kailin.traffic.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
class LocationHelper(context: Context) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest = LocationRequest.create()?.apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    private var locationCallback: LocationCallback? = null

    fun lastLocation(onSuccess: (Location?) -> Unit, onFailure: (Exception) -> Unit) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFailure)
    }

    fun requestLocationUpdates(
        onSuccess: (Location) -> Unit,
        onFailure: (Exception) -> Unit,
        frequency: Int = 1
    ) {
        locationCallback = object : LocationCallback() {
            var t = 0
            override fun onLocationResult(p0: LocationResult?) {
                p0?.let {
                    onSuccess(it.lastLocation)
                    t++
                    if (t >= frequency) {
                        fusedLocationClient.removeLocationUpdates(locationCallback)
                        locationCallback = null
                    }
                }
            }
        }
        fusedLocationClient
            .requestLocationUpdates(
                locationRequest, locationCallback, Looper.getMainLooper()
            ).addOnFailureListener(onFailure)
    }
}