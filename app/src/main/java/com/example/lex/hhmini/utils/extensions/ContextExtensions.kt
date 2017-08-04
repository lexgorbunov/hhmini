package com.example.lex.hhmini.utils.extensions

import com.example.lex.hhmini.App
import com.example.lex.hhmini.presentation.base.BaseView

fun BaseView.getApp(): App {
    return getApplicationContext() as App
}