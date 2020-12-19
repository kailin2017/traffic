package com.kailin.traffic.util

import androidx.annotation.StringRes
import com.kailin.traffic.app.MyApplication

class StringUtil private constructor() {

    fun getString(@StringRes stringId: Int): String {
        return MyApplication.instance.getString(stringId)
    }

    companion object {
        val instance: StringUtil by lazy { StringUtil() }
    }
}