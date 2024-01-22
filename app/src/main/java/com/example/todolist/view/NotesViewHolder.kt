package com.example.todolist.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.data.local.Note
import com.example.todolist.databinding.ItemNoteBinding

class NotesViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
    fun bind(item: Note, clickListener: ItemActionListener) {
        binding.timeCreated.text = item.time
        if (item.note.isEmpty()) {
            binding.noteText.visibility = View.GONE
        } else {
            binding.noteText.text = item.note
        }
        if (item.title.isEmpty()) {
            binding.titleText.visibility = View.GONE
        } else {
            binding.titleText.text = item.title
        }
        itemView.setOnClickListener {
            clickListener.onItemClick(item)
        }
        itemView.setOnLongClickListener { view ->
            clickListener.onItemLongClick(item, view)
            true
        }


    }

}


