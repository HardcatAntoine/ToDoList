package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.Nodes
import com.example.todolist.data.repository.NodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CreateNodeViewModel @Inject constructor(
    private val repository: NodesRepository
) : ViewModel() {
     fun insertNode(title: String, nodeText: String) {
        viewModelScope.launch {
            val currentTime = SimpleDateFormat(
                "dd.MM.yyyy HH:mm",
                Locale.getDefault()
            ).format(Calendar.getInstance().time)
            val node = Nodes(
                null,
                title = title,
                noteText = nodeText,
                time = currentTime
            )
            repository.insertNode(node)
        }
    }
}