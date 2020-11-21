package com.kailin.traffic.data.bus.version

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BusVersionData")
data class BusVersionData(
    @PrimaryKey
    var CityName: String = "",
    val UpdateCheckTime: String,
    val UpdateTime: String,
    val VersionID: Int
)