package com.example.lex.hhmini.injections.app

import com.example.lex.hhmini.data.VacanciesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServicesModule {
    @Provides
    @Singleton
    fun providesVacanciesService(retrofit: Retrofit): VacanciesService = retrofit.create(VacanciesService::class.java)
}
