package com.example.lex.hhmini.data.db

import android.database.Cursor
import android.database.sqlite.SQLiteStatement
import android.text.TextUtils

object DB {
    const val TABLE_VACANCIES: String = "vacancies"
    const val TABLE_SALARIES: String = "salaries"
    const val TABLE_EMPLOYER: String = "employer"
    const val BOOLEAN_TRUE = 1

    fun dropVacancies(): String {
        return "DROP TABLE IF EXISTS ${TABLE_VACANCIES}"
    }

    fun dropSalaries(): String {
        return "DROP TABLE IF EXISTS ${TABLE_SALARIES}"
    }

    fun dropEmployer(): String {
        return "DROP TABLE IF EXISTS ${TABLE_EMPLOYER}"
    }


    fun createVacancies(): String {
        val stringBuilder = StringBuilder("CREATE TABLE IF NOT EXISTS ${TABLE_VACANCIES}(")

        stringBuilder.append("id").append(" INTEGER PRIMARY KEY AUTOINCREMENT").append(",")
        stringBuilder.append("vacancy_id").append(" TEXT NOT NULL").append(",")
        stringBuilder.append("name").append(" TEXT NOT NULL").append(",")
        stringBuilder.append("salary_id").append(" ").append(" INTEGER").append(",")
        stringBuilder.append("employer_id").append(" ").append(" INTEGER")
        stringBuilder.append(")")
        return stringBuilder.toString()
    }


    fun createSalaries(): String {
        val stringBuilder = StringBuilder("CREATE TABLE IF NOT EXISTS ${TABLE_SALARIES}(")

        stringBuilder.append("id").append(" INTEGER PRIMARY KEY AUTOINCREMENT").append(",")
        stringBuilder.append("to_val").append(" ").append(" INTEGER").append(",")
        stringBuilder.append("from_val").append(" ").append(" INTEGER").append(",")
        stringBuilder.append("gross").append(" ").append(" INTEGER").append(",")
        stringBuilder.append("currency").append(" ").append(" TEXT")
        stringBuilder.append(")")
        return stringBuilder.toString()
    }


    fun createEmployer(): String {
        val stringBuilder = StringBuilder("CREATE TABLE IF NOT EXISTS ${TABLE_EMPLOYER}(")

        stringBuilder.append("id").append(" INTEGER PRIMARY KEY AUTOINCREMENT").append(",")
        stringBuilder.append("name").append(" ").append(" TEXT").append(",")
        stringBuilder.append("url").append(" ").append(" TEXT").append(",")
        stringBuilder.append("trusted").append(" ").append(" INTEGER")
        stringBuilder.append(")")
        return stringBuilder.toString()
    }

    fun getString(cursor: Cursor, columnName: String): String {
        val columnIndex = cursor.getColumnIndex(columnName)
        if (columnIndex != -1) {
            return cursor.getString(columnIndex)
        } else {
            return ""
        }
    }

    fun getBoolean(cursor: Cursor, columnName: String): Boolean {
        return getInt(cursor, columnName) == BOOLEAN_TRUE
    }

    fun getLong(cursor: Cursor, columnName: String): Long {
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName))
    }

    fun notNull(cursor: Cursor, columnName: String): Boolean {
        val columnIndex = cursor.getColumnIndex(columnName)
        return columnIndex != -1 && !cursor.isNull(columnIndex)
    }

    fun getFloat(cursor: Cursor, columnName: String): Float {
        return cursor.getFloat(cursor.getColumnIndexOrThrow(columnName))
    }

    fun getDouble(cursor: Cursor, columnName: String): Double {
        val columnIndex = cursor.getColumnIndexOrThrow(columnName)
        if (columnIndex != -1) {
            return cursor.getDouble(columnIndex)
        } else {
            return 0.0
        }
    }

    fun getInt(cursor: Cursor, columnName: String): Int {
        val columnIndex = cursor.getColumnIndexOrThrow(columnName)
        if (columnIndex != -1) {
            return cursor.getInt(columnIndex)
        } else {
            return 0
        }
    }


    fun bindStringOrNull(sqLiteStatement: SQLiteStatement, index: Int, value: String?) {
        if (TextUtils.isEmpty(value)) {
            sqLiteStatement.bindNull(index)
        } else {
            sqLiteStatement.bindString(index, value)
        }
    }

}
