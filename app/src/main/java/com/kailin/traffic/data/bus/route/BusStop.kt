package com.kailin.traffic.data.bus.route

import com.kailin.traffic.data.bus.NameType
import java.io.Serializable

data class BusStop(
    val LocationCityCode: String,
    val StationID: String,
    val StopBoarding: Int,
    val StopID: String,
    val StopName: NameType,
    val StopPosition: BusStopPosition,
    val StopSequence: Int,
    val StopUID: String
) : Serializable