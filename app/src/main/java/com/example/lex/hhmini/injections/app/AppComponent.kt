package com.example.lex.hhmini.injections.app

import android.content.Context
import com.example.lex.hhmini.data.retrofit.VacanciesService
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Component(modules = arrayOf(
        AppModule::class, OkHttpClientModule::class,
        RetrofitModule::class, ServicesModule::class))
@Singleton
interface AppComponent {
    fun retrofit(): Retrofit
    fun okHttpClient(): OkHttpClient
    fun vacanciesService(): VacanciesService
}
