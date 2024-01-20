package com.example.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.Note
import com.example.todolist.data.repository.NotesRepository
import com.example.todolist.view.TextBuffer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class UpdateNoteViewModel @Inject constructor(
    private val repository: NotesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val note: Note = savedStateHandle["note"] ?: Note(null, "", "", "")
    private val textBuffer = TextBuffer(note)

    private val _doneBtnIsVisibleState = MutableLiveData<Boolean>(false)
    val doneBtnIsVisibleState: LiveData<Boolean>
        get() = _doneBtnIsVisibleState

    private val _isUndoBtnEnabled = MutableLiveData<Boolean>(false)
    val isUndoBtnEnabled: LiveData<Boolean>
        get() = _isUndoBtnEnabled

    private val _isRedoBtnEnabled = MutableLiveData<Boolean>(false)
    val isRedoBtnEnabled: LiveData<Boolean>
        get() = _isRedoBtnEnabled


    fun updateDoneBtnVisibility(isVisible: Boolean) {
        _doneBtnIsVisibleState.value = isVisible
    }

    fun enableUndoBtn() {
        _isUndoBtnEnabled.value = true
    }

    fun enableRedoBtn() {
        _isRedoBtnEnabled.value = true
    }

    fun undo(): Note {
        val resultUndo = textBuffer.undo()!!
        _isUndoBtnEnabled.value = !resultUndo.isBound
        _isRedoBtnEnabled.value = resultUndo.isBound
        return resultUndo.note
    }

    fun redo(): Note {
        val resultRedo = textBuffer.redo()!!
        _isRedoBtnEnabled.value = !resultRedo.isBound
        _isUndoBtnEnabled.value = resultRedo.isBound
        return resultRedo.note
    }

    fun noteChanged(text: String) {
        textBuffer.noteChanged(text)
    }

    fun titleChanged(title: String) {
        textBuffer.titleChanged(title)
    }


    fun updateNote(id: Int?, title: String, noteText: String) {
        viewModelScope.launch {
            val currentTime = SimpleDateFormat(
                "dd.MM.yyyy HH:mm",
                Locale.getDefault()
            ).format(Calendar.getInstance().time)
            val note = Note(
                id,
                title = title,
                note = noteText,
                time = currentTime
            )
            repository.updateNote(note)
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            repository.removeNote(note)
        }
    }
}