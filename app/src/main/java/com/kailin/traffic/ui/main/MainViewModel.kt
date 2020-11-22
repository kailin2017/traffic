package com.kailin.traffic.ui.main

import com.kailin.traffic.app.BaseViewModel
import com.kailin.traffic.data.APIConfig
import com.kailin.traffic.data.bus.BusDB
import com.kailin.traffic.data.bus.BusService
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.data.bus.version.BusVersionData

class MainViewModel : BaseViewModel() {

    private val busService = BusService.instance
    private val busDao = BusDB.instance.busDao

    fun initData() {
        APIConfig.API_URL_V2_BUS_CITY_ARRAY.forEach { initRoomBusVersion(it) }
    }

    // 取得ROOM中的版本資料
    private fun initRoomBusVersion(cityName: String) {
        rxJavaHelper.maybe(this, busDao.getBusVersionData(cityName), {
            initAPIBusVersion(cityName) { apiIT ->
                if (it.VersionID != apiIT.VersionID) {
                    insertBusVersion(cityName, apiIT)
                    initBusRoute(cityName)
                }
            }
        }, {
            initAPIBusVersion(cityName) { apiIT ->
                insertBusVersion(cityName, apiIT)
                initBusRoute(cityName)
            }
        })
    }

    // 取得API的版本資料
    private fun initAPIBusVersion(cityName: String, callBack: (BusVersionData) -> Unit) {
        rxJavaHelper.single(this, busService.getBusVersion(cityName), callBack)
    }

    // 將版本資料寫入ROOM
    private fun insertBusVersion(cityName: String, busVersionData: BusVersionData) {
        busVersionData.CityName = cityName
        rxJavaHelper.single(this, busDao.insertBusVersionData(busVersionData)) {}
    }

    // 將路線資料寫入ROOM
    private fun initBusRoute(cityName: String) {
        rxJavaHelper.single(this, busService.getBusRoute(cityName)) {
            insertBusRouteData(it)
            msgText.postValue("initBusRoute OK! $cityName")
        }
    }

    private fun insertBusRouteData(data: List<BusRouteData>) {
        rxJavaHelper.single(this, busDao.insertBusRouteData(data)) {}
    }
}