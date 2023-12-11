package com.example.todolist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class NodesAdapter : RecyclerView.Adapter<NodesViewHolder>() {
    private var list: List<String> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodesViewHolder {
        val holder = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_node, parent, false)
        return NodesViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NodesViewHolder, position: Int) {
        val item = list[position]
        holder.bind()
    }

    fun setList(list: List<String>){
        this.list = list
        notifyDataSetChanged()
    }
}