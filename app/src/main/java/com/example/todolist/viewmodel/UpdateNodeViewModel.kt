package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.Nodes
import com.example.todolist.data.repository.NodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateNodeViewModel @Inject constructor(
    private val repository: NodesRepository
) : ViewModel() {
    fun updateNode(node: Nodes) {
        viewModelScope.launch {
            repository.updateNode(node)
        }
    }
}