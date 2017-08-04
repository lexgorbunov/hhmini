package com.example.lex.hhmini.domain

import io.reactivex.Single


interface BaseUseCase<T> {
    fun execute(): Single<T>
}