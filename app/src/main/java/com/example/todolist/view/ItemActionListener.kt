package com.example.todolist.view

import android.view.View
import com.example.todolist.data.local.Nodes

interface ItemActionListener {
    fun onItemLongClick(node: Nodes, view: View)
    fun onItemClick(node: Nodes)
}