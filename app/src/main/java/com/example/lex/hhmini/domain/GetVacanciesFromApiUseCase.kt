package com.example.lex.hhmini.domain

import android.util.Log
import com.example.lex.hhmini.data.VacanciesService
import com.example.lex.hhmini.data.models.Vacancy
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GetVacanciesFromApiUseCase @Inject constructor(var vacanciesService: VacanciesService) : GetVacanciesUseCase {
    override fun getList(searchText: String, page: Int): Single<List<Vacancy>> {
        Log.d("LeX", "Load from API")
        return vacanciesService.loadVacancies(searchText, page)
                .subscribeOn(Schedulers.io())
                .map { it.items }
                .observeOn(AndroidSchedulers.mainThread())
    }
}