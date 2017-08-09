package com.example.lex.hhmini.di.main

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.example.lex.hhmini.presentation.main.MainView
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ForStartView

@Module
class MainViewModule(val view: MainView, val context: Context) {
    @Provides
    @ForStartView
    fun provideContext(): Context = context

    @Provides
    @ForStartView
    fun provideView(): MainView = view

    @Provides
    @ForStartView
    fun provideLayoutManager(context: Context): LinearLayoutManager =
            LinearLayoutManager(context)
}
