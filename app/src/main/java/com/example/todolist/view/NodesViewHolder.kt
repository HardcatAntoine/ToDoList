package com.example.todolist.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.data.local.Nodes
import com.example.todolist.databinding.ItemNodeBinding
import java.text.SimpleDateFormat
import java.util.Locale

class NodesViewHolder(private val binding: ItemNodeBinding) : ViewHolder(binding.root) {
    fun bind(item: Nodes, clickListener: ClickListeners) {
        binding.titleText.text = item.name
        binding.timeCreated.text = item.time
        if (item.description.isNullOrEmpty()){
            binding.nodeText.visibility = View.GONE
        }else {
            binding.nodeText.text = item.description
        }
        itemView.setOnClickListener {
            clickListener.onItemClickListener(item)
        }

    }
}