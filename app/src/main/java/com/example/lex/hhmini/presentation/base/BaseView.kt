package com.example.lex.hhmini.presentation.base

import android.content.Context
import android.widget.Toast

interface BaseView {
    fun getApplicationContext(): Context
    fun displayError(message: String) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show()
    }
}

//abstract class BaseViewImpl : BaseView {
//    override fun getContext(): Context {
//        return this
//    }
//}