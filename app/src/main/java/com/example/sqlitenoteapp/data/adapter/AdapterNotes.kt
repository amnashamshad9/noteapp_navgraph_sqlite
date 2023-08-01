package com.example.sqlitenoteapp.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.db.DatabaseHelper
import com.example.sqlitenoteapp.data.model.ModelNote
import com.example.sqlitenoteapp.databinding.ItemRowBinding
import com.example.sqlitenoteapp.ui.ListFragmentDirections

class AdapterNotes(val noteList:ArrayList<ModelNote>) : RecyclerView.Adapter<AdapterNotes.ViewHolderNotes>() {

    inner class ViewHolderNotes(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNotes {
        context = parent.context
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderNotes(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ViewHolderNotes, position: Int) {


        val dataList = noteList[position]
        holder.binding.titleTv.text = dataList.title
        holder.binding.descTv.text = dataList.description

        holder.binding.delBtn.setOnClickListener {
            val database = DatabaseHelper(context)
            val sqLiteDatabase = database.writableDatabase
            val id = dataList.id
            sqLiteDatabase.delete(DatabaseHelper.TABLE_NAME,"${DatabaseHelper.COL_ID} = $id",null)
            Toast.makeText(context,"${dataList.title} is deleted",Toast.LENGTH_LONG).show()
        }

        holder.binding.modifyBtn.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateNoteFragment(dataList.id)
            Navigation.findNavController(it).navigate(action)
        }

    }
}