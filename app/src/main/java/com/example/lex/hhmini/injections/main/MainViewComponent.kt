package com.example.lex.hhmini.injections.main

import com.example.lex.hhmini.injections.app.AppComponent
import com.example.lex.hhmini.ui.main.MainActivity
import dagger.Component


@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainViewModule::class))
@ForStartView
interface MainViewComponent {
    fun inject(target: MainActivity)
}
