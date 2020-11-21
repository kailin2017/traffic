package com.kailin.traffic.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val msgText = MutableLiveData<String>()
    val loading = MutableLiveData(false)
}