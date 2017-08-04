package com.example.lex.hhmini.utils.rx_utils

import android.support.annotation.CallSuper
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable


open class SimpleSingleObserver<T> : SingleObserver<T> {
    override fun onSubscribe(d: Disposable) {}

    @CallSuper
    override fun onError(e: Throwable) {
        e.printStackTrace()
    }

    override fun onSuccess(t: T) {}
}