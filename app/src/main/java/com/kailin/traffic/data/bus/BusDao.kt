package com.kailin.traffic.data.bus

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.data.bus.version.BusVersionData
import io.reactivex.rxjava3.core.Single

interface BusDao {

    @Query("SELECT * FROM BusVersionData WHERE CityName = :CityName AND VersionID < :VersionID ")
    fun getBusVersionData(CityName: String, VersionID: Int): Single<List<BusVersionData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusVersionData(data: List<BusVersionData>): Single<List<Long>>

    @Query("SELECT * FROM BusRouteData WHERE RouteName LIKE '%' || :routeName || '%' LIMIT 15")
    fun getBusRouteData(routeName: String): Single<List<BusRouteData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusRouteData(data: List<BusRouteData>): Single<List<Long>>
}