package com.kailin.traffic.util

import android.content.Context
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.libraries.maps.model.PolylineOptions
import com.kailin.traffic.data.bus.route.BusStopOfRoute

class MapHelper(private val context: Context, private val googleMap: GoogleMap) {

    fun moveCameraByLocation(onFailure: (Exception) -> Unit) {
        LocationHelper(context).requestLocationUpdates(
            {
                val latLng = LatLng(it.latitude, it.longitude)
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14F)
                googleMap.moveCamera(cameraUpdate)
            }, onFailure
        )
    }

    fun drawBusStopOfRoute(busStopOfRoute: BusStopOfRoute) {
        googleMap.clear()
        val rectOptions = PolylineOptions()
        busStopOfRoute.Stops.forEach {
            val latLng = LatLng(it.StopPosition.PositionLat, it.StopPosition.PositionLon)
            rectOptions.add(latLng)
            googleMap.addMarker(MarkerOptions().title(it.StopName.Zh_tw).position(latLng))
        }
        googleMap.addPolyline(rectOptions)
    }
}