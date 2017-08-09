package com.example.lex.hhmini.di.app

import com.example.lex.hhmini.data.VacanciesService
import com.example.lex.hhmini.data.db.DBHelper
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = arrayOf(
        AppModule::class, OkHttpClientModule::class,
        RetrofitModule::class, ServicesModule::class,
        DBModule::class))
@Singleton
interface AppComponent {
    fun retrofit(): Retrofit
    fun okHttpClient(): OkHttpClient
    fun vacanciesService(): VacanciesService
    fun dbHelper(): DBHelper
}
