package com.example.lex.hhmini.data.models

import com.google.gson.annotations.SerializedName


data class Vacancy(@SerializedName("id") val vacancy_id: String, val name: String,
                   val salary: Salary?, val employer: Employer) {

    data class Salary(val from: Long?, val to: Long?,
                      val gross: Boolean?, val currency: String)

    data class Employer(val id: Long, val name: String,
                        val url: String, val trusted: Boolean)

}
