package com.example.lex.hhmini.di.app

import com.example.lex.hhmini.BuildConfig
import com.example.lex.hhmini.data.retrofit.UserAgentInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
class OkHttpClientModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            clientBuilder.addInterceptor(loggingInterceptor)
        }
        clientBuilder.addNetworkInterceptor(UserAgentInterceptor())
        return clientBuilder.build()
    }
}
