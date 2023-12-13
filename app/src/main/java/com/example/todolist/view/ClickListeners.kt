package com.example.todolist.view

import com.example.todolist.data.local.Nodes

interface ClickListeners {
    fun removeNode(node: Nodes)
    fun onItemClickListener(node: Nodes)
}