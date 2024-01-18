package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.Note
import com.example.todolist.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {
     fun insertNote(titleText: String, noteText: String) {
        viewModelScope.launch {
            val currentTime = SimpleDateFormat(
                "dd.MM.yyyy HH:mm",
                Locale.getDefault()
            ).format(Calendar.getInstance().time)
            val note = Note(
                null,
                title = titleText,
                note = noteText,
                time = currentTime
            )
            repository.insertNote(note)
        }
    }
}