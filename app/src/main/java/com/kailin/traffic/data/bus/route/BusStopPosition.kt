package com.kailin.traffic.data.bus.route

data class BusStopPosition(
    val GeoHash: String,
    val PositionLat: Double,
    val PositionLon: Double
)