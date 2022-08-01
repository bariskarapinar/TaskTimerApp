package com.gamebit.tasktimerapp

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.util.Log

const val CONTENT_AUTHORITY = "com.gamebit.tasktimerapp.provider"

private const val TASKS = 100
private const val TASKS_ID = 101

private const val TIMINGS = 200
private const val TIMINGS_ID = 201

private const val TASK_DURATIONS = 400
private const val TASK_DURATIONS_ID = 401

val CONTENT_AUTHORITY_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

class AppProvider: ContentProvider() {

    private val uriMatcher by lazy { buildUriMatcher() }

    private fun buildUriMatcher(): UriMatcher {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)

        matcher.addURI(CONTENT_AUTHORITY, TaskContracts.TABLE_NAME, TASKS)
        matcher.addURI(CONTENT_AUTHORITY, "${TaskContracts.TABLE_NAME}/#", TASKS_ID)

        /*matcher.addURI(CONTENT_AUTHORITY, TimingsContract.TABLE_NAME, TIMINGS)
        matcher.addURI(CONTENT_AUTHORITY, "${TimingsContract.TABLE_NAME}/#", TIMINGS_ID)

        matcher.addURI(CONTENT_AUTHORITY, DurationsContract.TABLE_NAME, TASK_DURATIONS)
        matcher.addURI(CONTENT_AUTHORITY, "${DurationsContract.TABLE_NAME}/#", TASK_DURATIONS_ID)*/

        return matcher
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?,
                       sortOrder: String?): Cursor? {
        val match = uriMatcher.match(uri)
        val queryBuilder: SQLiteQueryBuilder = SQLiteQueryBuilder()

        when (match) {
            TASKS -> queryBuilder.tables = TaskContracts.TABLE_NAME

            TASKS_ID -> {
                queryBuilder.tables = TaskContracts.TABLE_NAME
                val taskId = TaskContracts.getId(uri)
                queryBuilder.appendWhere("${TaskContracts.Columns.ID} = $taskId")
            }

            /*TIMINGS -> queryBuilder.tables = TimingsContract.TABLE_NAME

            TIMINGS_ID -> {
                queryBuilder.tables = TimingsContract.TABLE_NAME
                val timingId = TimingsContract.getId(uri)
                queryBuilder.appendWhereEscapeString("${TimingsContract.Columns.ID} = $timingId")
            }

            TASK_DURATIONS -> queryBuilder.tables = DurationsContract.TABLE_NAME

            TASK_DURATIONS_ID -> {
                queryBuilder.tables = DurationsContract.TABLE_NAME
                val durationId = DurationsContract.getId(uri)
                queryBuilder.appendWhereEscapeString("${DurationsContract.Columns.ID} = $durationId")
            }*/

            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        val db = context?.let { AppDatabase.getInstance(it).readableDatabase }
        val cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder)

        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
}
