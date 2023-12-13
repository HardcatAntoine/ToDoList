package com.example.todolist.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "nodes")
data class Nodes(
    @PrimaryKey val name: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("time") val time: String
) : Parcelable