package com.example.lex.hhmini.injections.app

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return context
    }
}