package com.example.todolist.view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.data.local.Nodes
import com.example.todolist.databinding.ItemNodeBinding

class NodesViewHolder(private val binding: ItemNodeBinding) : ViewHolder(binding.root) {
    fun bind(item: Nodes, clickListener: ClickListeners) {
        binding.nodeName.text = item.name
        binding.cardView.setOnClickListener {
            clickListener.onItemClickListener(item)
        }
    }
}