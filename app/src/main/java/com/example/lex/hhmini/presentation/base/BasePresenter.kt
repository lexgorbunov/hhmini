package com.example.lex.hhmini.presentation.base

import android.support.annotation.CallSuper
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer


interface BasePresenter {
    var subscriptions: CompositeDisposable

    fun <T> Single<T>.makeSubscribe(onSuccess: Consumer<T>, onError: Consumer<Throwable>? = null): Single<T> {
        val s = this.subscribe(onSuccess, onError ?: Consumer<Throwable> { it.printStackTrace() })
        subscriptions.add(s)
        return this
    }

    fun <T> Single<T>.makeSubscribe(disposable: Disposable): Single<T> {
        subscriptions.add(disposable)
        return this
    }

    @CallSuper
    fun onDestroy() {
        subscriptions.clear()
    }
}

class BasePresenterImpl : BasePresenter {
    override var subscriptions: CompositeDisposable = CompositeDisposable()
}