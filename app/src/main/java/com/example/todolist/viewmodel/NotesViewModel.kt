package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.Note
import com.example.todolist.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {
    private val _listNote = MutableLiveData<List<Note>>()
    val listNote: LiveData<List<Note>>
        get() = _listNote

    fun getNotesList() {
        viewModelScope.launch {
            _listNote.value = repository.getListNotes()
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            _listNote.value = repository.removeNote(note)
        }
    }

}