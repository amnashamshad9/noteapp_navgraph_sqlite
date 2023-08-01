package com.example.sqlitenoteapp.ui

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sqlite.db.DatabaseHelper
import com.example.sqlitenoteapp.R
import com.example.sqlitenoteapp.data.adapter.AdapterNotes
import com.example.sqlitenoteapp.data.model.ModelNote
import com.example.sqlitenoteapp.databinding.FragmentListBinding


class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    lateinit var database: DatabaseHelper
    lateinit var sqliteDb: SQLiteDatabase
    lateinit var cursor: Cursor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = DatabaseHelper(requireContext())
        sqliteDb = database.writableDatabase
        cursor =  sqliteDb.rawQuery(DatabaseHelper.READ_TABLE, null)

        val adapter = AdapterNotes(getAllNotes())
        binding.notesLv.adapter = adapter

        binding.addNoteImgBtn.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }
    }

    fun getAllNotes(): ArrayList<ModelNote> {
        val list = ArrayList<ModelNote>()
        while (cursor.moveToNext()) {

            val idIndex: Int = cursor.getColumnIndex(DatabaseHelper.COL_ID)
            val titleIndex: Int = cursor.getColumnIndex(DatabaseHelper.COL_TITLE)
            val descIndex: Int = cursor.getColumnIndex(DatabaseHelper.COL_DESC)

            val id: Int = cursor.getInt(idIndex)
            val title: String = cursor.getString(titleIndex)
            val desc: String = cursor.getString(descIndex)

            val note = ModelNote(id, title, desc)
            Log.d("NOKHAIZ", "getAllNotes: $note")
            list.add(note)
        }
        return list
    }
}