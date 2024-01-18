package com.example.todolist.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("note_text") val note: String,
    @ColumnInfo("time") val time: String
) : Parcelable