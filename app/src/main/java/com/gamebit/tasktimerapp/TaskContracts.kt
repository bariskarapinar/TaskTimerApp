package com.gamebit.tasktimerapp

import android.provider.BaseColumns

object TaskContracts {

    internal const val TABLE_NAME = "Tasks"

    // Tasks field
    object Columns {
        const val ID = BaseColumns._ID
        const val TASKS_NAME = "Name"
        const val TASKS_DESCRIPTION = "Description"
        const val TASK_SORT_ORDER = "SortOrder"
    }
}