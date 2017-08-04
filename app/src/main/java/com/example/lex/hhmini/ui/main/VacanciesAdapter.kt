package com.example.lex.hhmini.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.lex.hhmini.R
import com.example.lex.hhmini.data.models.Vacancy
import javax.inject.Inject


class VacanciesAdapter @Inject constructor() : RecyclerView.Adapter<VacancyHolder>() {
    private var data: MutableList<Vacancy> = mutableListOf()

    fun setData(list: List<Vacancy>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<Vacancy>) {
        val size = data.size
        data.addAll(list)
        notifyItemRangeInserted(size, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyHolder {
        val holder = VacancyHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_vacancy, parent, false))
        return holder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VacancyHolder?, position: Int) {
        holder?.bind(data[position])
    }

}