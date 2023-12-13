package com.example.todolist.data.repository

import androidx.annotation.WorkerThread
import com.example.todolist.data.local.Nodes
import com.example.todolist.data.local.NodesDao
import javax.inject.Inject

class NodesRepository @Inject constructor(private val dao: NodesDao) {
    @WorkerThread
    suspend fun insertNode(node: Nodes) {
        dao.insertNode(node)
    }

    @WorkerThread
    suspend fun removeNode(node: Nodes): List<Nodes> {
        dao.removeNode(node)
        return dao.getListNodes()
    }

    @WorkerThread
    suspend fun getListNodes(): List<Nodes> {
        return dao.getListNodes()
    }
}