package com.example.lex.hhmini.di.main

import com.example.lex.hhmini.di.app.AppComponent
import com.example.lex.hhmini.presentation.main.MainActivity
import dagger.Component


@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainViewModule::class))
@ForStartView
interface MainViewComponent {
    fun inject(target: MainActivity)
}
