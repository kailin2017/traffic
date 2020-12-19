package com.kailin.traffic.app

import android.app.Application
import com.kailin.traffic.util.firebase.AnalyticsUtil

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        AnalyticsUtil.instance
    }

    companion object {
        lateinit var instance: MyApplication
    }
}