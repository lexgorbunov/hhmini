package com.example.lex.hhmini.data

import android.database.Cursor
import android.database.SQLException
import com.example.lex.hhmini.data.db.DB
import com.example.lex.hhmini.data.db.DBHelper
import com.example.lex.hhmini.data.models.Vacancy
import io.reactivex.Single
import javax.inject.Inject

class VacancyRepository @Inject constructor(val dbHelper: DBHelper) {
    val salaryRepository: SalaryRepository
    val employerRepository: EmployerRepository

    init {
        salaryRepository = SalaryRepository(dbHelper)
        employerRepository = EmployerRepository(dbHelper)
    }

    fun getList(searchText: String, page: Int): Single<List<Vacancy>> {
        return Single.just(findVacancies())
    }

    fun selectAllFields(): String {
        return """
            ${DB.TABLE_VACANCIES}.vacancy_id as ${DB.TABLE_VACANCIES}_vacancy_id,
            ${DB.TABLE_VACANCIES}.name as ${DB.TABLE_VACANCIES}_name,
            ${DB.TABLE_VACANCIES}.salary_id as ${DB.TABLE_VACANCIES}_salary_id,
            ${DB.TABLE_VACANCIES}.employer_id as ${DB.TABLE_VACANCIES}_employer_id
        """
    }

    fun findVacancies(): List<Vacancy> {
        val database = dbHelper.writableDatabase
        val sql = """SELECT
            ${selectAllFields()},
            ${salaryRepository.selectAllFields()},
            ${employerRepository.selectAllFields()}

        FROM ${DB.TABLE_VACANCIES}
        LEFT JOIN ${DB.TABLE_SALARIES} on ${DB.TABLE_VACANCIES}.salary_id = ${DB.TABLE_SALARIES}.id
        LEFT JOIN ${DB.TABLE_EMPLOYER} on ${DB.TABLE_VACANCIES}.employer_id = ${DB.TABLE_EMPLOYER}.id"""
        val cursor = database.rawQuery(sql, null)
        val vacanciesList = mutableListOf<Vacancy>()

        cursor.use {
            while (it.moveToNext()) {
                vacanciesList.add(fromCursor(it))
            }
        }

        return vacanciesList
    }

    private fun fromCursor(cursor: Cursor): Vacancy {
        val salaryId = if (DB.notNull(cursor, "${DB.TABLE_VACANCIES}_salary_id")) DB.getLong(cursor, "${DB.TABLE_VACANCIES}_salary_id") else -1
        val salary = if (salaryId != -1L) salaryRepository.fromCursor(cursor) else null

//        val employer = employerRepository.findOne(DB.getLong(cursor, "employer_id"))!!
        val employer = employerRepository.fromCursor(cursor)

        return Vacancy(DB.getString(cursor, "${DB.TABLE_VACANCIES}_vacancy_id"),
                DB.getString(cursor, "${DB.TABLE_VACANCIES}_name"),
                salary, employer)
    }

    fun saveList(list: List<Vacancy>): Boolean {
        val database = dbHelper.writableDatabase

        database.execSQL("DELETE FROM ${DB.TABLE_VACANCIES}")

        val insertStatement = database.compileStatement("INSERT INTO ${DB.TABLE_VACANCIES} (vacancy_id, name, salary_id, employer_id) VALUES(?, ?, ?, ?)")

        try {
            database.beginTransaction()

            for (vacancy in list) {
                insertStatement.clearBindings()
                insertStatement.bindString(1, vacancy.vacancy_id)
                insertStatement.bindString(2, vacancy.name)
                // TODO Связанная сущность
                if (vacancy.salary != null) {
                    insertStatement.bindLong(3, salaryRepository.saveOne(vacancy.salary))
                } else {
                    insertStatement.bindNull(3)
                }
                insertStatement.bindLong(4, employerRepository.saveOne(vacancy.employer))
                insertStatement.executeInsert()
            }
            database.setTransactionSuccessful()
            return true
        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return false
        } finally {
            database.endTransaction()
            insertStatement.close()
        }
    }

//    fun getSubCategories(parentId: Long): List<Vacancy> {
//        val database = dbHelper.writableDatabase
//
//        val cursor = database.rawQuery("SELECT * FROM categories WHERE parent_id = ? ORDER BY placement_index ASC", arrayOf(parentId.toString()))
//
//        val vacanciesList = ArrayList<Category>(cursor.count + 1)
//
//        while (cursor.moveToNext()) {
//            val category = fromCursor(cursor)
//            category.isHasParameters = getBoolean(cursor, "has_parameters")
//            vacanciesList.add(category)
//        }
//
//        cursor.close()
//
//        return vacanciesList
//    }

    val count: Int
        get() {
            var countInDb = 0
            val database = dbHelper.writableDatabase
            val cursor = database.rawQuery("SELECT count(*) as totalSize FROM ${DB.TABLE_VACANCIES}", null)

            if (cursor.count > 0) {
                cursor.moveToFirst()
                countInDb = DB.getInt(cursor, "totalSize")
            }
            cursor.close()

            return countInDb
        }
}
