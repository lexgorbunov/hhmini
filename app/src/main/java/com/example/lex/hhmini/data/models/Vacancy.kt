package com.example.lex.hhmini.data.models


data class Vacancy(val id: String, val name: String, val salary: Salary?, val employer: Employer) {
    data class Salary(val to: Long?, val from: Long?, val gross: Boolean?, val currency: String)
    data class Employer(val id: Long, val name: String, val url: String, val trusted: Boolean)
}