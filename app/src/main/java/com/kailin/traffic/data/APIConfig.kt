package com.kailin.traffic.data

object APIConfig {

    const val API_URL_V2_BASIC_PTX_NEWS = "/MOTC/v2/PTX/Web/News"

    val API_URL_V2_BUS_CITY_ARRAY = arrayOf("Taipei", "NewTaipei", "Taoyuan")
    const val API_URL_V2_BUS_ROUTE = "/MOTC/v2/Bus/Route/City"
    const val API_URL_V2_BUS_VERSION = "/MOTC/v2/Bus/DataVersion/City"
    const val API_URL_V2_BUS_ESTIMATED_TIME = "/MOTC/v2/Bus/EstimatedTimeOfArrival/City"
    const val API_URL_V2_BUS_BUS_STOP_OF_ROUTE = "/MOTC/v2/Bus/StopOfRoute/City"
}