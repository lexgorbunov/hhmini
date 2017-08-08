package com.example.lex.hhmini.data

import android.database.Cursor
import android.database.SQLException
import com.example.lex.hhmini.data.db.DB
import com.example.lex.hhmini.data.db.DBHelper
import com.example.lex.hhmini.data.models.Vacancy
import javax.inject.Inject

class EmployerDao @Inject constructor(val dbHelper: DBHelper) {

    fun findOne(id: Long): Vacancy.Employer? {
        val database = dbHelper.writableDatabase
        val sql = "SELECT * FROM ${DB.TABLE_EMPLOYER} WHERE id = $id LIMIT 1"
        val cursor = database.rawQuery(sql, null)

        cursor.use {
            return if (it.moveToFirst()) fromCursor(it) else null
        }
    }

    private fun fromCursor(cursor: Cursor): Vacancy.Employer =
            Vacancy.Employer(DB.getLong(cursor, "id"), DB.getString(cursor, "name"),
                DB.getString(cursor, "url"), DB.getInt(cursor, "trusted") == DB.BOOLEAN_TRUE)


    fun saveOne(entry: Vacancy.Employer): Long {
        val database = dbHelper.writableDatabase

        database.execSQL("DELETE FROM ${DB.TABLE_EMPLOYER} WHERE id = ?", arrayOf(entry.id))

        val insertStatement = database.compileStatement("INSERT INTO ${DB.TABLE_EMPLOYER} (id, name, url, trusted) VALUES(?, ?, ?, ?)")

        try {
            database.beginTransaction()

            insertStatement.clearBindings()
            insertStatement.bindLong(1, entry.id)
            insertStatement.bindString(2, entry.name)
            insertStatement.bindString(3, entry.url)
            insertStatement.bindLong(4, if (entry.trusted) 1 else 0)
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
