package com.example.todolist.data.repository

import androidx.annotation.WorkerThread
import com.example.todolist.data.local.Note
import com.example.todolist.data.local.NotesDao
import javax.inject.Inject

class NotesRepository @Inject constructor(private val dao: NotesDao) {
    @WorkerThread
    suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    @WorkerThread
    suspend fun removeNote(note: Note): List<Note> {
        dao.removeNote(note)
        return dao.getListNotes()
    }

    @WorkerThread
    suspend fun updateNote(note: Note): List<Note> {
        dao.updateNote(note)
        return dao.getListNotes()
    }

    @WorkerThread
    suspend fun getListNotes(): List<Note> {
        return dao.getListNotes()
    }
}