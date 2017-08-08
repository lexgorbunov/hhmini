package com.example.lex.hhmini.injections.app

import android.content.Context
import com.example.lex.hhmini.data.db.DBHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {
    @Provides
    @Singleton
    fun providesDB(context: Context): DBHelper = DBHelper(context)
}
