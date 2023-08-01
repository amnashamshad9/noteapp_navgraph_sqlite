package com.example.sqlitenoteapp.ui

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.sqlite.db.DatabaseHelper
import com.example.sqlitenoteapp.databinding.FragmentUpdateNoteBinding

class UpdateNoteFragment : Fragment() {
    lateinit var binding:FragmentUpdateNoteBinding
    lateinit var databaseHelper: DatabaseHelper
    lateinit var sqlDB:SQLiteDatabase
    val args:UpdateNoteFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseHelper = DatabaseHelper(requireContext())
        sqlDB = databaseHelper.writableDatabase

        binding.saveNoteBtn.setOnClickListener {
            val upTitle = binding.etTitle.text.toString()
            val upDescription = binding.etDescription.text.toString()
            val cv = ContentValues()
            cv.put(DatabaseHelper.COL_TITLE,upTitle)
            cv.put(DatabaseHelper.COL_DESC,upDescription)
            val id = args.id
            sqlDB.update(DatabaseHelper.TABLE_NAME,cv,"${DatabaseHelper.COL_ID} = $id",null)
            Toast.makeText(requireContext(),"Update Note",Toast.LENGTH_LONG).show()
        }
    }
}