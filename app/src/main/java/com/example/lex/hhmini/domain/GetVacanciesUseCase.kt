package com.example.lex.hhmini.domain

import com.example.lex.hhmini.data.models.Vacancy
import io.reactivex.Single


interface GetVacanciesUseCase {
    fun getList(searchText: String, page: Int): Single<List<Vacancy>>
}