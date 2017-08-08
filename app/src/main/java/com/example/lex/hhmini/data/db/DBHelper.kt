package com.example.lex.hhmini.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DB.createEmployer())
        db.execSQL(DB.createSalaries())
        db.execSQL(DB.createVacancies())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DB.dropEmployer())
        db.execSQL(DB.dropSalaries())
        db.execSQL(DB.dropVacancies())
        onCreate(db)
    }

    companion object {
        val VERSION = 1
        private val DATABASE_NAME = "hhmini.db"
    }
}
