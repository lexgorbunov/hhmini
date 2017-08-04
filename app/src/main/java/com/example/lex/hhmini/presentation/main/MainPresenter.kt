package com.example.lex.hhmini.presentation.main

import com.example.lex.hhmini.presentation.base.BasePresenter
import com.example.lex.hhmini.presentation.base.BasePresenterImpl


abstract class MainPresenter : BasePresenter by BasePresenterImpl() {
    abstract fun loadVacancies(page: Int)
}