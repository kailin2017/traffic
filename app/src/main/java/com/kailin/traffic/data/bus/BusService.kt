package com.kailin.traffic.data.bus

import com.kailin.traffic.data.APIConfig
import com.kailin.traffic.data.bus.estimatedTime.EstimateTimeData
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.data.bus.route.BusStopOfRoute
import com.kailin.traffic.data.bus.version.BusVersionData
import com.kailin.traffic.util.connect.RetrofitManager
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BusService {

    @GET("${APIConfig.API_URL_V2_BUS_VERSION}/{city}")
    fun getBusVersion(@Path("city") city: String): Single<BusVersionData>

    @GET("${APIConfig.API_URL_V2_BUS_ROUTE}/{city}")
    fun getBusRoute(@Path("city") city: String): Single<MutableList<BusRouteData>>

    @GET("${APIConfig.API_URL_V2_BUS_ESTIMATED_TIME}/{city}/{route}")
    fun getEstimatedTime(
        @Path("city") city: String,
        @Path("route") route: String,
        @Query("\$filter") filter: String,
    ): Single<MutableList<EstimateTimeData>>

    @GET("${APIConfig.API_URL_V2_BUS_BUS_STOP_OF_ROUTE}/{city}/{route}")
    fun getBusStopOfRoute(
        @Path("city") city: String,
        @Path("route") route: String,
        @Query("\$filter") filter: String,
    ): Single<MutableList<BusStopOfRoute>>

    companion object {
        val instance: BusService by lazy {
            RetrofitManager.instance.create(BusService::class.java)
        }

        fun filterRouteUidString(uid: String) = "RouteUID eq '$uid'"
    }
}