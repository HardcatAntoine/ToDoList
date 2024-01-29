package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.Note
import com.example.todolist.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

    private val _notes = MutableStateFlow(listOf(Note(note = "", title = "", time = "")))
    val notes = _notes.asStateFlow()

    fun getNotesList() {
        viewModelScope.launch {
            _notes.update { repository.getListNotes() }
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            _notes.update { repository.removeNote(note) }
        }
    }

}