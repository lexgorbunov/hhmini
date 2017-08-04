package com.example.lex.hhmini.presentation.main

import com.example.lex.hhmini.data.models.Vacancy
import com.example.lex.hhmini.presentation.base.BaseView


interface MainView : BaseView {
    fun displayVacancies(list: List<Vacancy>, page: Int)
}
