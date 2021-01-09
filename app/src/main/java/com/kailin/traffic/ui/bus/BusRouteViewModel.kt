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
    val reciprocal: MutableLiveData<Int> by lazy { MutableLiveData(reciprocalDefault) }
    val selectedPage: MutableLiveData<Int> by lazy { MutableLiveData(reciprocalDefault) }
    private val busService = BusService.instance
    private lateinit var routeFilter: String
    private lateinit var routeCity: String
    private lateinit var routeString: String

    fun initData(city: String, route: String, routeUid: String) {
        routeFilter = BusService.filterRouteUidString(routeUid)
        routeCity = city
        routeString = route
        rxJavaHelper.single(
            this@BusRouteViewModel,
            busService.getBusStopOfRoute(city, route, routeFilter)
        ) {
            busStopOfRoute.postValue(it)
        }
        updateEstimateTime()
    }


    private fun updateEstimateTime() {
        rxJavaHelper.single(
            this,
            busService.getEstimatedTime(routeCity, routeString, routeFilter)
        ) {
            estimateTimeData.postValue(it)
            reciprocalReset()
        }
    }

    private fun reciprocalReset() {
        reciprocal.postValue(reciprocalDefault)
        rxJavaHelper.reciprocal(this, 1, reciprocalDefault.toLong(), tag, {
            reciprocal.postValue((reciprocalDefault - it).toInt())
        }) {
            updateEstimateTime()
        }
    }

    override fun onCleared() {
        super.onCleared()
        rxJavaHelper.dispose(tag)
    }

    companion object {
        const val reciprocalDefault = 20
        private const val tag = "BusRouteViewModel"
    }
}