package com.example.todolist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun removeNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT*FROM notes")
    suspend fun getListNotes(): List<Note>
}