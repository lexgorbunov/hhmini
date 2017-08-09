package com.example.lex.hhmini.domain

import android.util.Log
import com.example.lex.hhmini.data.VacancyRepository
import com.example.lex.hhmini.data.models.Vacancy
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GetVacanciesFromDBUseCase @Inject constructor(val vacancyDay: VacancyRepository) : GetVacanciesUseCase {
    override fun getList(searchText: String, page: Int): Single<List<Vacancy>> {
        Log.d("LeX", "Load from DB")
        return if (page == 1) {
            vacancyDay.getList(searchText, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        } else {
            return Single.just(emptyList())
        }
    }
}