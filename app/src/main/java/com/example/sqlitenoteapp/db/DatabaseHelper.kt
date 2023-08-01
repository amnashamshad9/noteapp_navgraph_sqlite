package com.example.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(
        context, DATABASE_NAME, null, VERSION_NAME
    ) {

    companion object {
        const val DATABASE_NAME = "notes"
        const val VERSION_NAME = 1
        const val TABLE_NAME = "notes_table"
        const val COL_ID = "_id"
        const val COL_TITLE = "title"
        const val COL_DESC = "description"

        // Creating Table Query:
        const val CREATE_TABLE =
            "create table $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_TITLE TEXT NOT NULL, $COL_DESC TEXT NOT NULL)"

        const val READ_TABLE =
            "SELECT  * FROM  $TABLE_NAME"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}