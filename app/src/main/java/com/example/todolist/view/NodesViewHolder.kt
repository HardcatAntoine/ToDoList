package com.example.todolist.view

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.R
import com.example.todolist.data.local.Nodes

class NodesViewHolder(itemView: View) : ViewHolder(itemView) {
    private val nodeName = itemView.findViewById<TextView>(R.id.node_name)
    private val remove = itemView.findViewById<Button>(R.id.remove_btn)

    fun bind(item : Nodes) {
        nodeName.text = item.name

    }
}