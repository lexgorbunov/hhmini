package com.example.lex.hhmini.presentation.main

import com.example.lex.hhmini.R
import com.example.lex.hhmini.data.models.Vacancy
import com.example.lex.hhmini.domain.GetVacanciesFromApiUseCase
import com.example.lex.hhmini.domain.GetVacanciesFromDBUseCase
import com.example.lex.hhmini.domain.SaveVacanciesToDBUseCase
import com.example.lex.hhmini.presentation.base.BasePresenter
import com.example.lex.hhmini.presentation.base.BasePresenterImpl
import io.reactivex.functions.Consumer
import javax.inject.Inject

open class MainViewPresenter @Inject constructor(
        val view: MainView,
        val loadFeedApiUseCase: GetVacanciesFromApiUseCase,
        val loadFeedDbUseCase: GetVacanciesFromDBUseCase,
        val saveFeedUseCase: SaveVacanciesToDBUseCase) : BasePresenter by BasePresenterImpl() {

    fun loadVacancies(page: Int) {
//        loadFeedApiUseCase.getList(SEARCH_TEXT, page)
//                .doOnSuccess {
//                    saveFeedUseCase.saveList(it, page)
//                }
//                .onErrorResumeNext {
//                    loadFeedDbUseCase.getList(SEARCH_TEXT, page)
//                }
//                .makeSubscribe(Consumer<List<Vacancy>> {
//                    view.displayVacancies(it, page)
//                }, Consumer<Throwable> {
//                    view.displayError(view.getApplicationContext()
//                            .getString(R.string.error_vacancies_loading))
//                })
        loadFeedDbUseCase.getList(SEARCH_TEXT, page)
                .makeSubscribe(Consumer<List<Vacancy>> {
                    view.displayVacancies(it, page)
                }, Consumer<Throwable> {
                    view.displayError(view.getApplicationContext()
                            .getString(R.string.error_vacancies_loading))
                })
    }

    companion object {
        const val SEARCH_TEXT = "Android"
    }
}
