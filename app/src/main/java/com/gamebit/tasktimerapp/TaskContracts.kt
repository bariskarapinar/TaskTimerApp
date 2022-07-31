package com.gamebit.tasktimerapp

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

object TaskContracts {

    internal const val TABLE_NAME = "Tasks"
    val CONTENT_URI: Uri = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME)

    const val CONTENT_TYPE = "vnd.android.cursor.dir//vnd.$CONTENT_AUTHORITY.$TABLE_NAME"
    const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"

    // Tasks field
    object Columns {
        const val ID = BaseColumns._ID
        const val TASKS_NAME = "Name"
        const val TASKS_DESCRIPTION = "Description"
        const val TASK_SORT_ORDER = "SortOrder"
    }

    fun getId(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id: Long): Uri {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }
}
