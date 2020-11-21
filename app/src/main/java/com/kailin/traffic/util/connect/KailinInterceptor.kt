package com.kailin.traffic.util.connect

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


class KailinInterceptor private constructor() : Interceptor {

    private val appId = "ddf6503c26f84c178e0910cad2cf87a9"
    private val appKey = "yZHThZrgPlGleR-h7JPbhhZz0js"
    private val utcTime: String by lazy {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US)
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        dateFormat.format(calendar.time)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val signature: String = hSHA1("x-date: $utcTime", appKey)
        val authorization =
            "hmac username=\"$appId\", algorithm=\"hmac-sha1\", headers=\"x-date\", signature=\"$signature\""

        val request = chain.request().newBuilder().run {
            addHeader("Authorization", authorization)
            addHeader("x-date", utcTime)
            build()
        }
        return chain.proceed(request)
    }

    @Suppress("SameParameterValue")
    private fun hSHA1(data: String, kString: String): String {
        return try {
            val algorithm = "HmacSHA1"
            val result = Mac.getInstance(algorithm).run {
                val signingKey = SecretKeySpec(kString.toByteArray(charset("UTF-8")), algorithm)
                init(signingKey)
                doFinal(data.toByteArray(charset("UTF-8")))
            }
            Base64.encodeToString(result, Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
            data
        }
    }

    companion object {
        val instance: KailinInterceptor by lazy { KailinInterceptor() }
    }
}