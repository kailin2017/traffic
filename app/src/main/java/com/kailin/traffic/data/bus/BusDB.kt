package com.kailin.traffic.data.bus

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kailin.traffic.app.KailinApplication
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.data.bus.version.BusVersionData
import io.reactivex.rxjava3.core.Single

@Database(
    entities = [BusVersionData::class, BusRouteData::class],
    version = 1,
    exportSchema = false
)
abstract class BusDB : RoomDatabase() ,BusDao{

    protected abstract val busDao: BusDao

    override fun getBusVersionData(CityName: String, VersionID: Int): Single<List<BusVersionData>> {
        return busDao.getBusVersionData(CityName, VersionID)
    }

    override fun insertBusVersionData(data: List<BusVersionData>): Single<List<Long>> {
        return busDao.insertBusVersionData(data)
    }

    override fun getBusRouteData(routeName: String): Single<List<BusRouteData>> {
        return busDao.getBusRouteData(routeName)
    }

    override fun insertBusRouteData(data: List<BusRouteData>): Single<List<Long>> {
        return busDao.insertBusRouteData(data)
    }

    companion object {
        val instance: BusDB by lazy {
            Room.databaseBuilder(KailinApplication.instance, BusDB::class.java, "BusDB.db").build()
        }
    }
}