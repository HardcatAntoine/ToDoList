package com.example.todolist.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.todolist.data.local.Note
import com.example.todolist.view.compose_components.AddNoteButton
import com.example.todolist.view.compose_components.NotesList
import com.example.todolist.view.compose_components.TopBar
import com.example.todolist.viewmodel.NotesViewModel

@Composable
fun MainScreen(
    viewModel: NotesViewModel,
    navController: NavController
) {
    val notes = viewModel.notes.collectAsState()
    Box {
        Column(Modifier.fillMaxSize()) {
            TopBar()
            NotesList(notes = notes.value, navController = navController, viewModel = viewModel)
        }
        AddNoteButton(boxScope = this, navController = navController)
    }
}



