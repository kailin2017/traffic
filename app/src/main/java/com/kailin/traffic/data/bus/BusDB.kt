package com.kailin.traffic.data.bus

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kailin.traffic.app.MyApplication
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.data.bus.version.BusVersionData

@Database(
    entities = [BusVersionData::class, BusRouteData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BusDBConverters::class)
abstract class BusDB : RoomDatabase() {

    abstract val busDao: BusDao

    companion object {
        val instance: BusDB by lazy {
            Room.databaseBuilder(MyApplication.instance, BusDB::class.java, "BusDB.db").build()
        }
    }
}