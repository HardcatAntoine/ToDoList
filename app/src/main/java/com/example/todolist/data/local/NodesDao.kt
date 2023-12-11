package com.example.todolist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NodesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNode(node: Nodes)

    @Delete
    suspend fun removeNode(node: Nodes)

    @Query("SELECT*FROM nodes")
    suspend fun getListNodes(): List<Nodes>
}