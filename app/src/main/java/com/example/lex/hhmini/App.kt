package com.example.lex.hhmini

import android.app.Application
import com.example.lex.hhmini.di.app.AppComponent
import com.example.lex.hhmini.di.app.AppModule
import com.example.lex.hhmini.di.app.DaggerAppComponent


class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}