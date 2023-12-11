package com.example.todolist.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nodes")
data class Nodes(
    @PrimaryKey val name: String,
    @ColumnInfo("description") val description: String
)