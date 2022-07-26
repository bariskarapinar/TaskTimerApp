package com.gamebit.tasktimerapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val projection = arrayOf(TasksContract.Columns.TASKS_NAME, TasksContract.Columns.TASK_SORT_ORDER)
        val sortColumn = TasksContract.Columns.TASK_SORT_ORDER

        val cursor = contentResolver.query(TasksContract.CONTENT_URI, null, null, null, sortColumn)
        Log.d(TAG, "********************")
        cursor.use {
            while (it!!.moveToNext()) {
                // cycle through all records
                with(cursor) {
                    val id = this?.getLong(0)
                    val name = this?.getString(1)
                    val description = this?.getString(2)
                    val sortOrder = this?.getString(3)
                    val result = "ID: $id. Name: $name. Description: $description Sort Order: $sortOrder."
                    Log.d(TAG, "Result: $result")
                }
            }
        }
        Log.d(TAG, "********************")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.menumain_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
