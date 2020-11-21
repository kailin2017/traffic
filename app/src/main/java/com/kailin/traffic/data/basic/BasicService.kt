package com.kailin.traffic.data.basic

import com.kailin.traffic.data.APIConfig
import com.kailin.traffic.data.basic.news.PTXNewsData
import com.kailin.traffic.util.connect.RetrofitManager
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BasicService {

    @GET("${APIConfig.API_URL_V2_BASIC_PTX_NEWS}?\$top=30&\$format=JSON")
    fun getPTXNews(): Single<PTXNewsData>

    companion object {
        val instance: BasicService by lazy {
            RetrofitManager.instance.create(BasicService::class.java)
        }
    }
}