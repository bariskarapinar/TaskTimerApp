package com.gamebit.tasktimerapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

private const val TAG = "AppDatabase"
private const val DATABASE_NAME = "TaskTimer.db"
private const val DATABASE_VERSION = 1

internal class AppDatabase constructor(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        Log.d(TAG, "AppDatabase: init")
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate Starts")
        val sSQL = """CREATE TABLE ${TaskContracts.TABLE_NAME} (
            ${TaskContracts.Columns.ID} INTEGER PRIMARY KEY NOT NULL, 
            ${TaskContracts.Columns.TASKS_NAME} TEXT NOT NULL,
            ${TaskContracts.Columns.TASKS_DESCRIPTION} TEXT,
            ${TaskContracts.Columns.TASK_SORT_ORDER} INTEGER);""".replaceIndent(" ")
        Log.d(TAG, sSQL)
        db.execSQL(sSQL)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
