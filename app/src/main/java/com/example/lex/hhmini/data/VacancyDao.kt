package com.example.lex.hhmini.data

import android.database.Cursor
import android.database.SQLException
import com.example.lex.hhmini.data.db.DB
import com.example.lex.hhmini.data.db.DBHelper
import com.example.lex.hhmini.data.models.Vacancy
import io.reactivex.Single
import javax.inject.Inject

class VacancyDao @Inject constructor(val dbHelper: DBHelper) {
    val salaryDao: SalaryDao
    val employerDao: EmployerDao

    init {
        salaryDao = SalaryDao(dbHelper)
        employerDao = EmployerDao(dbHelper)
    }

    fun getList(searchText: String, page: Int): Single<List<Vacancy>> {
        return Single.just(findVacancies())
    }

    fun findVacancies(): List<Vacancy> {
        val database = dbHelper.writableDatabase
        val sql = "SELECT * FROM ${DB.TABLE_VACANCIES}"
        val cursor = database.rawQuery(sql, null)
        val vacanciesList = mutableListOf<Vacancy>()

        while (cursor.moveToNext()) {
            vacanciesList.add(fromCursor(cursor))
        }
        cursor.close()

        return vacanciesList
    }

    private fun fromCursor(cursor: Cursor): Vacancy {
        val salaryId = if (DB.notNull(cursor, "salary_id")) DB.getLong(cursor, "salary_id") else -1
        val salary = if (salaryId != -1L) salaryDao.findOne(salaryId) else null

        val employer = employerDao.findOne(DB.getLong(cursor, "employer_id"))!!

        return Vacancy(DB.getString(cursor, "vacancy_id"),
                DB.getString(cursor, "name"),
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
                    insertStatement.bindLong(3, salaryDao.saveOne(vacancy.salary))
                } else {
                    insertStatement.bindNull(3)
                }
                insertStatement.bindLong(4, employerDao.saveOne(vacancy.employer))
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
