package com.gamebit.tasktimerapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.IllegalStateException

private const val TAG = "AppDatabase"
private const val DATABASE_NAME = "TaskTimer.db"
private const val DATABASE_VERSION = 1

internal class AppDatabase private constructor(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        Log.d(TAG, "AppDatabase: init")
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate Starts")
        val sSQL = """CREATE TABLE ${TasksContract.TABLE_NAME} (
            ${TasksContract.Columns.ID} INTEGER PRIMARY KEY NOT NULL, 
            ${TasksContract.Columns.TASKS_NAME} TEXT NOT NULL,
            ${TasksContract.Columns.TASKS_DESCRIPTION} TEXT,
            ${TasksContract.Columns.TASK_SORT_ORDER} INTEGER);""".replaceIndent(" ")
        Log.d(TAG, sSQL)
        db.execSQL(sSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade: starts")
        when(oldVersion) {
            1 -> {
                // Upgrade Logic from V1
            }
            else -> throw IllegalStateException("onUpgrade() with unknown version: $newVersion")
        }
    }

    companion object : SingletonHolder<AppDatabase, Context> (::AppDatabase)

    /*companion object {
        @Volatile
        private var instance: AppDatabase? = null
        fun getInstance( context: Context): AppDatabase =
            instance?: synchronized(this) {
                instance ?: AppDatabase(context).also {
                    instance = it
                }
            }
    }*/
}
