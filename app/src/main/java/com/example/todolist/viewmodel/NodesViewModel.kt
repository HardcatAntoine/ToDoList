package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.Nodes
import com.example.todolist.data.repository.NodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NodesViewModel @Inject constructor(
    private val repository: NodesRepository
) : ViewModel() {
    private val _listNodes = MutableLiveData<List<Nodes>>()
    val listNodes: LiveData<List<Nodes>>
        get() = _listNodes

    fun getNodesList() {
        viewModelScope.launch {
            _listNodes.value = repository.getListNodes()
        }
    }

    fun removeNode(node: Nodes) {
        viewModelScope.launch {
            _listNodes.value = repository.removeNode(node)
        }
    }

}