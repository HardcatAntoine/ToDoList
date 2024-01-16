package com.example.todolist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.local.Note
import com.example.todolist.databinding.ItemNoteBinding

class NotesAdapter : RecyclerView.Adapter<NotesViewHolder>() {
    private var list: List<Note> = listOf()
    private var clickListener: ItemActionListener? = null
    fun setItemActionListener(clickListener: ItemActionListener) {
        this.clickListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, clickListener!!)
    }

    fun setList(list: List<Note>) {
        this.list = list
        notifyDataSetChanged()
    }
}