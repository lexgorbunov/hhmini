package com.example.lex.hhmini.data

import com.example.lex.hhmini.data.models.Vacancies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface VacanciesService {
    @GET("vacancies")
    fun loadVacancies(@Query("text") text: String, @Query("page") page: Int,
                      @Query("per_page") per_page: Int = 20): Single<Vacancies>
}