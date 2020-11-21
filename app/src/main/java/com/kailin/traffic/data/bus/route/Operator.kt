package com.kailin.traffic.data.bus.route

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kailin.traffic.data.bus.NameType
import com.kailin.traffic.util.GsonHelper

data class Operator(
    val OperatorCode: String,
    val OperatorID: String,
    val OperatorName: NameType,
    val OperatorNo: String
)

interface OperatorConverters {
    companion object {
        @TypeConverter
        fun toObject(value: String?): Operator? {
            val type = object : TypeToken<Operator?>() {}.type
            return GsonHelper.instance.gson.fromJson(value, type)
        }

        @TypeConverter
        fun toString(value: Operator?): String? {
            return GsonHelper.instance.gson.toJson(value)
        }
    }
}