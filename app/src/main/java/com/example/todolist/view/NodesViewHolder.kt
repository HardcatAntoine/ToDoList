package com.example.todolist.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.data.local.Nodes
import com.example.todolist.databinding.ItemNodeBinding

class NodesViewHolder(private val binding: ItemNodeBinding) : ViewHolder(binding.root) {
    fun bind(item: Nodes, clickListener: ItemActionListener) {
        binding.timeCreated.text = item.time
        if (item.noteText.isEmpty()) {
            binding.nodeText.visibility = View.GONE
        } else {
            binding.nodeText.text = item.noteText
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


