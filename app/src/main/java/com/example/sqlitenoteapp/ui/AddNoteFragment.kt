package com.example.sqlitenoteapp.ui

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sqlite.db.DatabaseHelper
import com.example.sqlitenoteapp.R
import com.example.sqlitenoteapp.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {
    lateinit var binding: FragmentAddNoteBinding
    lateinit var databaseHelper: DatabaseHelper // database create, open
    lateinit var sqliteDB: SQLiteDatabase // database -> insert, delete, update
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseHelper = DatabaseHelper(requireContext())
        sqliteDB = databaseHelper.writableDatabase

        binding.saveNoteBtn.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            // used to store data with respective columns
            val contentValues = ContentValues()
            contentValues.put(DatabaseHelper.COL_TITLE,title)
            contentValues.put(DatabaseHelper.COL_DESC,description)

            // used to store data in the table of the database
            sqliteDB.insert(DatabaseHelper.TABLE_NAME,null,contentValues)
            Toast.makeText(requireContext(),"Note Insert Successful",Toast.LENGTH_LONG).show()
        }
    }
}