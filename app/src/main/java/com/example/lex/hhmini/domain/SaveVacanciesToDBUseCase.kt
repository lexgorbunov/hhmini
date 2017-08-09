package com.example.lex.hhmini.domain

import android.util.Log
import com.example.lex.hhmini.data.VacancyRepository
import com.example.lex.hhmini.data.models.Vacancy
import javax.inject.Inject


class SaveVacanciesToDBUseCase @Inject constructor(val vacancyRepository: VacancyRepository) {
    fun saveList(list: List<Vacancy>, page: Int): Boolean {
        if (page == 1) {
            val success = vacancyRepository.saveList(list)
            Log.d("LeX", "Save to DB ${if(success) "success" else "fail"}")
            return success
        } else {
            return true
        }
    }
}