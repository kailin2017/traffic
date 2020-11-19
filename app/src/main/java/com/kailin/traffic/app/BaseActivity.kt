package com.kailin.traffic.app

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.kailin.traffic.util.firebase.AnalyticsUtil

abstract class BaseActivity : AppCompatActivity() {

    private val analyticsHelper = AnalyticsUtil()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        analyticsHelper.init()
    }
}