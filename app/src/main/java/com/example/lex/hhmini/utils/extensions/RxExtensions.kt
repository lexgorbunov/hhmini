package com.example.lex.hhmini.utils.extensions

import io.reactivex.Single

fun <T> Single<T>.logOnError() = this.doOnError { log(it) }

fun log(throwable: Throwable) {
    throwable.printStackTrace()
}