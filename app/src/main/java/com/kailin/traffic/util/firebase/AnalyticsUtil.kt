package com.kailin.traffic.util.firebase

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class AnalyticsUtil private constructor() {

    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    companion object {
        val instance: AnalyticsUtil by lazy { AnalyticsUtil() }
    }
}