package com.kailin.traffic.data.bus

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.kailin.traffic.util.GsonHelper

data class NameType(
    val En: String,
    val Zh_tw: String
)

interface NameTypeConverters {
    companion object {
        @TypeConverter
        fun toObject(value: String?): NameType? {
            val type = object : TypeToken<NameType?>() {}.type
            return GsonHelper.instance.gson.fromJson(value, type)
        }

        @TypeConverter
        fun toString(value: NameType?): String? {
            return GsonHelper.instance.gson.toJson(value)
        }
    }
}