package com.example.todolist.view

import android.util.Log
import com.example.todolist.data.local.Note

data class NotesBufferElement(val note: Note, val isBound: Boolean)
class TextBuffer(note: Note) {
    private val changeTextBuffer = mutableListOf(note)
    private var currentTextBufferIndex = changeTextBuffer.size - 1
    fun undo(): NotesBufferElement? {
        if (currentTextBufferIndex <= 0) {
            Log.d(
                "UNDO",
                "UNDO: Current index is $currentTextBufferIndex. You can't go any further left."
            )
            return null
        }
        currentTextBufferIndex -= 1
        Log.d(
            "UNDO",
            "UNDO: Index before taking the item: $currentTextBufferIndex | size: ${changeTextBuffer.size})"
        )
        return when {
            currentTextBufferIndex >= 1 -> {
                NotesBufferElement(
                    note = changeTextBuffer[currentTextBufferIndex],
                    isBound = false
                )
            }

            currentTextBufferIndex == 0 -> {
                NotesBufferElement(
                    note = changeTextBuffer[currentTextBufferIndex],
                    isBound = true,
                )
            }

            else -> null
        }
    }

    fun redo(): NotesBufferElement? {
        if (currentTextBufferIndex >= changeTextBuffer.size - 1) {
            Log.d(
                "REDO",
                "REDO: Current index is $currentTextBufferIndex. You can't go any further right."
            )
            return null
        }
        currentTextBufferIndex += 1
        Log.d(
            "REDO",
            "REDO: Index before taking the item: $currentTextBufferIndex  | size: ${changeTextBuffer.size}"
        )
        return when {
            currentTextBufferIndex == (changeTextBuffer.size - 1) -> {
                NotesBufferElement(
                    note = changeTextBuffer[currentTextBufferIndex],
                    isBound = true,
                )
            }

            currentTextBufferIndex < changeTextBuffer.size -> {
                NotesBufferElement(
                    note = changeTextBuffer[currentTextBufferIndex],
                    isBound = false,
                )
            }

            else -> null
        }
    }

    fun noteChanged(text: String) {
        val lastChange = changeTextBuffer.last()
        val newChange = lastChange.copy(note = text)

        Log.d("Note changed", "Add to buffer: $newChange" )

        changeTextBuffer.add(newChange)
        currentTextBufferIndex = changeTextBuffer.size - 1
    }

    fun titleChanged(title: String) {
        val lastChange = changeTextBuffer.last()
        val newChange = lastChange.copy(title = title)

        Log.d("Title changed", "Add to buffer: $newChange" )

        changeTextBuffer.add(newChange)
        currentTextBufferIndex = changeTextBuffer.size - 1
    }

}