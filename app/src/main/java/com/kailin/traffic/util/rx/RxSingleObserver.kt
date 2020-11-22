package com.kailin.traffic.util.rx

import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

class RxSingleObserver<T> private constructor(
    private val callBackError: (Throwable) -> Unit,
    private val callBackSuccess: (T) -> Unit
) : SingleObserver<T> {

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onSuccess(t: T) = callBackSuccess(t)

    override fun onError(e: Throwable) = callBackError(e)

    companion object {
        fun <T> create(
            callBackError: (Throwable) -> Unit,
            callBackSuccess: (T) -> Unit
        ): RxSingleObserver<T> = RxSingleObserver(callBackError, callBackSuccess)
    }
}