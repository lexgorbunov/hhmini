package com.example.lex.hhmini.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.lex.hhmini.data.models.Vacancy
import kotlinx.android.synthetic.main.list_item_vacancy.view.*


class VacancyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val vacancyName: TextView

    init {
        vacancyName = itemView.vacancy_name
    }

    fun bind(data: Vacancy) {
        vacancyName.text = data.name
    }
}