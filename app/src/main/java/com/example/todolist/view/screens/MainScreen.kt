package com.example.todolist.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.todolist.data.local.Note
import com.example.todolist.view.compose_components.AddNoteButton
import com.example.todolist.view.compose_components.NotesList
import com.example.todolist.view.compose_components.TopBar
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    notesList: StateFlow<List<Note>>,
    onItemClick: (Note) -> Unit,
    onDeleteClick: (Note) -> Unit,
    onAddButtonClick: () -> Unit
) {
    val notes = notesList.collectAsState()
    Scaffold(
        topBar = { TopBar() },
        content = {
            Column(Modifier.fillMaxSize()) {
                NotesList(
                    notes = notes.value,
                    onItemClick = { onItemClick(it) },
                    onDeleteClick = { onDeleteClick(it) })
            }
        },
        floatingActionButton = {
            AddNoteButton(onAddButtonClick = { onAddButtonClick() })
        })
}



