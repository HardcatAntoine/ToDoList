package com.example.todolist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Nodes::class], version = 1)
abstract class NodesDatabase : RoomDatabase() {
    abstract fun nodesDao(): NodesDao
}