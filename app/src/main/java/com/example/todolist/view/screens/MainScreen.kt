package com.example.todolist.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.todolist.data.local.Note
import com.example.todolist.view.compose_components.AddNoteButton
import com.example.todolist.view.compose_components.NotesList
import com.example.todolist.view.compose_components.TopBar
import com.example.todolist.viewmodel.NotesViewModel

@Composable
fun MainScreen(
    notes: List<Note>,
    viewModel: NotesViewModel,
    navController: NavController
) {
    Box {
        Column(Modifier.fillMaxSize()) {
            TopBar()
            NotesList(notes = notes, navController = navController, viewModel = viewModel)
        }
        AddNoteButton(boxScope = this, navController = navController)
    }
}



