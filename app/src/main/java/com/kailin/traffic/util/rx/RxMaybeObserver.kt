package com.kailin.traffic.util.rx

import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.Disposable

class RxMaybeObserver<T> private constructor(
    private val callBackError: (Throwable) -> Unit,
    private val callBackSuccess: (T) -> Unit,
    private val callBackComplete: () -> Unit
) : MaybeObserver<T> {

    override fun onSubscribe(d: Disposable?) {
    }

    override fun onSuccess(t: T) = callBackSuccess(t)

    override fun onError(e: Throwable) = callBackError(e)

    override fun onComplete() = callBackComplete()

    companion object {
        fun <T> create(
            callBackError: (Throwable) -> Unit,
            callBackSuccess: (T) -> Unit,
            callBackComplete: () -> Unit
        ): RxMaybeObserver<T> = RxMaybeObserver(callBackError, callBackSuccess, callBackComplete)
    }
}