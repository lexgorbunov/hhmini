package com.example.lex.hhmini.domain

import com.example.lex.hhmini.data.models.Vacancies
import com.example.lex.hhmini.data.retrofit.VacanciesService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class GetVacanciesUseCase @Inject constructor(var vacanciesService: VacanciesService) : BaseUseCase<Vacancies> {
    val searchText = "Android"

    override fun execute(): Single<Vacancies> {
        return vacanciesService.loadVacancies(searchText).observeOn(AndroidSchedulers.mainThread())
    }
}