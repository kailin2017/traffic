package com.kailin.traffic.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kailin.traffic.util.rx.RxJavaHelper

abstract class BaseViewModel : ViewModel() {

    val msgText = MutableLiveData<String>()
    val loading = MutableLiveData(false)

    protected val rxJavaHelper = RxJavaHelper.instance

    fun onError(e: Throwable) {
        e.printStackTrace()
        msgText.postValue(e.message)
    }
}