package com.kailin.traffic.ui.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseViewModel
import com.kailin.traffic.data.bus.BusDB
import com.kailin.traffic.data.bus.route.BusRouteData

class HomeViewModel : BaseViewModel() {

    val searchBusRouteData = MutableLiveData<MutableList<BusRouteData>>()

    fun search(keyWord: String) {
        Log.e("search", keyWord)
        rxJavaHelper.maybe(
            vm = this,
            maybe = BusDB.instance.busDao.searchBusRouteData(keyWord),
            cbSuccess = { searchBusRouteData.postValue(it) },
            cbComplete = { msgText.postValue(getString(R.string.search_null)) }
        )
    }
}