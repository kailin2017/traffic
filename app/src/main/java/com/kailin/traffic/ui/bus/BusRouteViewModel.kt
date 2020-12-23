package com.kailin.traffic.ui.bus

import androidx.lifecycle.MutableLiveData
import com.kailin.traffic.app.BaseViewModel
import com.kailin.traffic.data.bus.BusService
import com.kailin.traffic.data.bus.estimatedTime.EstimateTimeData
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.data.bus.route.BusStopOfRoute

class BusRouteViewModel : BaseViewModel() {

    val busRouteData: MutableLiveData<BusRouteData> by lazy { MutableLiveData() }
    val busStopOfRoute: MutableLiveData<MutableList<BusStopOfRoute>> by lazy { MutableLiveData() }
    val estimateTimeData: MutableLiveData<MutableList<EstimateTimeData>> by lazy { MutableLiveData() }

    fun initData(city: String, route: String, routeUid: String) {
        val service = BusService.instance
        val filterString = BusService.filterRouteUidString(routeUid)
        rxJavaHelper.apply {
            single(this@BusRouteViewModel, service.getBusStopOfRoute(city, route, filterString)) {
                busStopOfRoute.postValue(it)
            }
            single(this@BusRouteViewModel, service.getEstimatedTime(city, route, filterString)) {
                estimateTimeData.postValue(it)
            }
        }
    }
}