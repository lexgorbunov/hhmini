package com.example.lex.hhmini.data

import android.database.Cursor
import android.database.SQLException
import com.example.lex.hhmini.data.db.DB
import com.example.lex.hhmini.data.db.DBHelper
import com.example.lex.hhmini.data.models.Vacancy
import javax.inject.Inject

class SalaryRepository @Inject constructor(val dbHelper: DBHelper) {

    fun selectAllFields(): String {
        return """
            ${DB.TABLE_SALARIES}.id as ${DB.TABLE_SALARIES}_id,
            ${DB.TABLE_SALARIES}.from_val as ${DB.TABLE_SALARIES}_from_val,
            ${DB.TABLE_SALARIES}.to_val as ${DB.TABLE_SALARIES}_to_val,
            ${DB.TABLE_SALARIES}.gross as ${DB.TABLE_SALARIES}_gross,
            ${DB.TABLE_SALARIES}.currency as ${DB.TABLE_SALARIES}_currency
        """
    }

    fun findOne(id: Long): Vacancy.Salary? {
        val database = dbHelper.writableDatabase
        val sql = """SELECT
            ${selectAllFields()}
            FROM ${DB.TABLE_SALARIES}
            WHERE ${DB.TABLE_SALARIES}.id = $id LIMIT 1"""
        val cursor = database.rawQuery(sql, null)

        cursor.use {
            return if (it.moveToFirst()) fromCursor(it) else null
        }
    }

    fun fromCursor(cursor: Cursor): Vacancy.Salary =
            Vacancy.Salary(DB.getLong(cursor, "${DB.TABLE_SALARIES}_from_val"), DB.getLong(cursor, "${DB.TABLE_SALARIES}_to_val"),
                DB.getInt(cursor, "${DB.TABLE_SALARIES}_gross") == DB.BOOLEAN_TRUE, DB.getString(cursor, "${DB.TABLE_SALARIES}_currency"))

    fun saveOne(entry: Vacancy.Salary): Long {
        val database = dbHelper.writableDatabase

        val insertStatement = database.compileStatement("INSERT INTO ${DB.TABLE_SALARIES} (from_val, to_val, gross, currency) VALUES(?, ?, ?, ?)")

        try {
            database.beginTransaction()

            insertStatement.clearBindings()
            if (entry.from == null) insertStatement.bindNull(1) else
                insertStatement.bindLong(1, entry.from)
            if (entry.to == null) insertStatement.bindNull(2) else
                insertStatement.bindLong(2, entry.to)
            if (entry.gross == null) insertStatement.bindNull(3) else
                insertStatement.bindLong(3, if (entry.gross) 1 else 0)
            insertStatement.bindString(4, entry.currency)
            val insertedId = insertStatement.executeInsert()

            database.setTransactionSuccessful()
            return insertedId
        } catch (e: SQLException) {
            e.printStackTrace()
            return -1
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return -1
        } finally {
            database.endTransaction()
            insertStatement.close()
        }
    }
}
