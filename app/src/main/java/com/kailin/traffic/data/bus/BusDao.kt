package com.kailin.traffic.data.bus

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.data.bus.version.BusVersionData
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface BusDao {

    @Query("SELECT * FROM BusVersionData WHERE CityName =:CityName")
    fun getBusVersionData(CityName: String): Maybe<BusVersionData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusVersionData(data: BusVersionData): Single<Long>

    @Query("SELECT * FROM BusRouteData WHERE RouteName LIKE '%' || :routeName || '%' LIMIT 15")
    fun getBusRouteData(routeName: String): List<BusRouteData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusRouteData(data: List<BusRouteData>): Single<List<Long>>
}