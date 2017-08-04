package com.example.lex.hhmini.data.retrofit

import com.example.lex.hhmini.data.models.Vacancies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface VacanciesService {
    @GET("vacancies")
    fun loadVacancies(@Query("text") text: String): Single<Vacancies>
}