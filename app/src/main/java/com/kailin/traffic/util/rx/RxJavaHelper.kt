package com.kailin.traffic.util.rx

import com.kailin.traffic.app.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxJavaHelper {

    private val disposableMap: MutableMap<String, Disposable> by lazy { mutableMapOf() }

    fun <T> maybe(
        vm: BaseViewModel,
        maybe: Maybe<T>,
        cbSuccess: (T) -> Unit,
        cbComplete: () -> Unit
    ) {
        maybe
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(RxMaybeObserver.create(vm::onError, cbSuccess, cbComplete))
    }

    fun <T> single(vm: BaseViewModel, single: Single<T>, callBack: (T) -> Unit) {
        single
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(RxSingleObserver.create(vm::onError, callBack))
    }

    fun <T, U, R> single2(
        vm: BaseViewModel,
        single1: Single<T>,
        single2: Single<U>,
        function: (T, U) -> R,
        callBack: (R) -> Unit
    ) {
        Single.zip(single1, single2, { t, u -> function(t, u) })
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(RxSingleObserver.create(vm::onError, callBack))
    }

    fun reciprocal(
        vm: BaseViewModel,
        frequency: Long,
        interval: Long,
        tag: String,
        cbConsumer: (Long) -> Unit,
        cnComplete: () -> Unit
    ) {
        disposableMap[tag] = Observable
            .interval(frequency, TimeUnit.SECONDS)
            .take(interval)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(cbConsumer, vm::onError, cnComplete)
    }

    fun dispose(tag: String) {
        disposableMap[tag]?.let {
            it.dispose()
            disposableMap.remove(tag)
        }
    }

    companion object {
        val instance: RxJavaHelper by lazy { RxJavaHelper() }
    }
}