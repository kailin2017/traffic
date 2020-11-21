package com.kailin.traffic.data.bus.route

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kailin.traffic.data.bus.NameType
import com.kailin.traffic.util.GsonHelper

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

interface SubRouteConverters {
    companion object {
        @TypeConverter
        fun toObject(value: String?): SubRoute? {
            val type = object : TypeToken<SubRoute?>() {}.type
            return GsonHelper.instance.gson.fromJson(value, type)
        }

        @TypeConverter
        fun toString(value: SubRoute?): String? {
            return GsonHelper.instance.gson.toJson(value)
        }
    }
}