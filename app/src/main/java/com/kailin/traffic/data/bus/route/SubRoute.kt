package com.kailin.traffic.data.bus.route

import com.kailin.traffic.data.bus.NameType

data class SubRoute(
    val Direction: Int,
    val FirstBusTime: String,
    val HolidayFirstBusTime: String,
    val HolidayLastBusTime: String,
    val LastBusTime: String,
    val OperatorIDs: List<String>,
    val SubRouteID: String,
    val SubRouteName: NameType,
    val SubRouteUID: String
)