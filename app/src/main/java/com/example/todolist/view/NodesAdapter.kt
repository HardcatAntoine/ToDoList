package com.example.todolist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.local.Nodes
import com.example.todolist.databinding.ItemNodeBinding

class NodesAdapter : RecyclerView.Adapter<NodesViewHolder>() {
    private var list: List<Nodes> = listOf()
    var clickListener: RemoveNodeClickListener? = null
    fun setRemoveNodeClickListener(clickListener: RemoveNodeClickListener) {
        this.clickListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodesViewHolder {
        val binding = ItemNodeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NodesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NodesViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, clickListener!!)
    }

    fun setList(list: List<Nodes>) {
        this.list = list
        notifyDataSetChanged()
    }
}