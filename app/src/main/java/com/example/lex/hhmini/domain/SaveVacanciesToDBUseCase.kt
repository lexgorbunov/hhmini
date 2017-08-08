package com.example.lex.hhmini.domain

import android.util.Log
import com.example.lex.hhmini.data.VacancyDao
import com.example.lex.hhmini.data.models.Vacancy
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SaveVacanciesToDBUseCase @Inject constructor(val vacancyDay: VacancyDao) {
    fun saveList(list: List<Vacancy>, page: Int): Boolean {
        if (page == 1) {
            val success = vacancyDay.saveList(list)
            Log.d("LeX", "Save to DB ${if(success) "success" else "fail"}")
            return success
        } else {
            return true
        }
    }
}