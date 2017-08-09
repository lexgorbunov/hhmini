package com.example.lex.hhmini.presentation.main

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.lex.hhmini.R
import com.example.lex.hhmini.data.models.Vacancy
import com.example.lex.hhmini.utils.extensions.gone
import com.example.lex.hhmini.utils.extensions.visible
import kotlinx.android.synthetic.main.list_item_vacancy.view.*


class VacancyHolder(itemView: View, val resources: Resources) : RecyclerView.ViewHolder(itemView) {
    val vacancyName: TextView = itemView.vacancy_name
    val salary: TextView = itemView.salary
    val company: TextView = itemView.company

    fun bind(data: Vacancy) {
        vacancyName.text = data.name

        val salBuilder: StringBuilder = StringBuilder()
        data.salary?.from?.let { salBuilder.append(resources.getString(R.string.salary_from, it)) }
        data.salary?.to?.let {
            if (salBuilder.isNotEmpty()) salBuilder.append(" ")
            salBuilder.append(resources.getString(R.string.salary_to, it))
        }
        data.salary?.currency?.let {
            if (salBuilder.isNotEmpty()) {
                salBuilder.append(" ")
                salBuilder.append(it)
            }
        }
        if (salBuilder.isEmpty()) {
            salary.gone()
        } else {
            salary.text = salBuilder.toString()
            salary.visible()
        }
        if (data.employer.name.isBlank()) {
            company.gone()
        } else {
            company.text = data.employer.name
            company.visible()
        }
    }
}