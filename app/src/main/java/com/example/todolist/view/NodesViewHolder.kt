package com.example.todolist.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.R
import com.example.todolist.data.local.Nodes
import com.example.todolist.databinding.ItemNodeBinding

class NodesViewHolder(private val binding: ItemNodeBinding) : ViewHolder(binding.root) {
    fun bind(item: Nodes, clickListener: RemoveNodeClickListener) {
        binding.nodeName.text = item.name
        binding.removeBtn.setOnClickListener {
            clickListener.removeNode(item)
        }
    }
}