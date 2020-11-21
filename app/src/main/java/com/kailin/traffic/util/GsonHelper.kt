package com.kailin.traffic.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonHelper private constructor() {

    val gson: Gson = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        .create()

    companion object {
        val instance: GsonHelper by lazy { GsonHelper() }
    }
}