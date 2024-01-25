package com.example.todolist.view

import android.view.View
import com.example.todolist.data.local.Note

interface ItemActionListener {
    fun onItemClick(note: Note)
}