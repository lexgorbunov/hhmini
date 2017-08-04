package com.example.lex.hhmini.presentation.main

import com.example.lex.hhmini.R
import com.example.lex.hhmini.data.models.Vacancies
import com.example.lex.hhmini.domain.GetVacanciesUseCase
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class MainViewPresenter @Inject constructor(val view: MainView) : MainPresenter() {
    @Inject
    protected lateinit var loadFeedUseCase: GetVacanciesUseCase

    override fun loadVacancies(page: Int) {
        loadFeedUseCase.execute()
                .subscribeOn(Schedulers.io())
                .makeSubscribe(Consumer<Vacancies> {
                    view.displayVacancies(it.items, page)
                }, Consumer<Throwable> { 
                    view.displayError(view.getApplicationContext().getString(R.string.error_vacancies_loading))
                })
    }
}
