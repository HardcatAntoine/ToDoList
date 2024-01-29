package com.example.todolist.view.compose_components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.data.local.Note
import com.example.todolist.viewmodel.NotesViewModel

@Composable
fun NotesList(notes: List<Note>, navController: NavController, viewModel: NotesViewModel) {
    LazyColumn(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        userScrollEnabled = true
    ) {
        notes.forEach { note ->
            item {
                NotesListItem(
                    navController = navController,
                    note = note,
                    viewModel = viewModel
                )
            }
        }
    }
}