package com.kailin.traffic.data.bus

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kailin.traffic.data.bus.route.Operator
import com.kailin.traffic.data.bus.route.SubRoute
import com.kailin.traffic.util.GsonHelper

object BusDBConverters {

    @TypeConverter
    fun string2NameType(value: String?): NameType {
        return try {
            GsonHelper.instance.gson.fromJson(value, object : TypeToken<NameType>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
            NameType()
        }
    }

    @TypeConverter
    fun nameType2String(value: NameType?): String {
        return if (value == null) {
            ""
        } else {
            GsonHelper.instance.gson.toJson(value)
        }
    }

    @TypeConverter
    fun string2Operator(value: String?): List<Operator> {
        return GsonHelper.instance.gson.fromJson(value, object : TypeToken<List<Operator>>() {}.type)
    }

    @TypeConverter
    fun operator2String(value: List<Operator>): String? {
        return GsonHelper.instance.gson.toJson(value)
    }

    @TypeConverter
    fun string2SubRoute(value: String?): List<SubRoute>? {
        return GsonHelper.instance.gson.fromJson(value, object : TypeToken<List<SubRoute>>() {}.type)
    }

    @TypeConverter
    fun subRoute2String(value: List<SubRoute>?): String? {
        return GsonHelper.instance.gson.toJson(value)
    }
}