package com.kailin.traffic.data.bus

import com.kailin.traffic.data.APIConfig
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.data.bus.version.BusVersionData
import com.kailin.traffic.util.connect.RetrofitManager
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BusService {

    @GET("${APIConfig.API_URL_V2_BUS_VERSION}/{city}?\$format=JSON")
    fun getBusVersion(@Path("city") city: String): Single<BusVersionData>

    @GET("${APIConfig.API_URL_V2_BUS_ROUTE}/{city}?\$format=JSON")
    fun getBusRoute(@Path("city") city: String): Single<MutableList<BusRouteData>>

    @GET("${APIConfig.API_URL_V2_BUS_ESTIMATED_TIME}/{city}/{route}?\$format=JSON")
    fun getEstimatedTime(@Path("city") city: String, @Path("route") route: String)

    companion object {
        val instance: BusService by lazy {
            RetrofitManager.instance.create(BusService::class.java)
        }
    }
}