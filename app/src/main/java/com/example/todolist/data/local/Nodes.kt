package com.example.todolist.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "nodes")
data class Nodes(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("note_text") val noteText: String,
    @ColumnInfo("time") val time: String
) : Parcelable