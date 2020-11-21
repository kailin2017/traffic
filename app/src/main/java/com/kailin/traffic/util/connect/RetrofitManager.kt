package com.kailin.traffic.util.connect

import com.kailin.traffic.R
import com.kailin.traffic.app.KailinApplication
import com.kailin.traffic.util.GsonHelper
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class RetrofitManager private constructor() {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.newThread()))
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.instance.gson))
            .baseUrl("https://ptx.transportdata.tw/")
            .client(createOkHttpClient())
            .build()
    }

    fun <T> create(service: Class<T>): T = retrofit.create(service)

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(KailinInterceptor.instance)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(20, TimeUnit.SECONDS)
        initSSLSocketFactory(builder)
        return builder.build()
    }

    private fun initSSLSocketFactory(builder: OkHttpClient.Builder) {
        try {
            val keyStorePassword = "fuckPassword".toCharArray()

            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType()).apply {
                val inputStream = KailinApplication.instance.resources.openRawResource(R.raw.ptx)
                load(null, keyStorePassword)
                setCertificateEntry(
                    "ptx",
                    CertificateFactory.getInstance("X.509").generateCertificate(inputStream)
                )
                inputStream.close()
            }

            val keyManagerFactory =
                KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()).apply {
                    init(keyStore, keyStorePassword)
                }
            val trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).apply {
                    init(keyStore)
                }

            val sslContext = SSLContext.getInstance("TLSv1.2").apply {
                init(
                    keyManagerFactory.keyManagers,
                    trustManagerFactory.trustManagers,
                    SecureRandom()
                )
            }

            trustManagerFactory.trustManagers[0].apply {
                if (this is X509TrustManager) {
                    builder.sslSocketFactory(sslContext.socketFactory, this)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        val instance: RetrofitManager by lazy { RetrofitManager() }
    }
}