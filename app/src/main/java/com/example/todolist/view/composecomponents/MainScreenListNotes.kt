package com.example.todolist.view.composecomponents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.data.local.Note
import com.example.todolist.view.MainFragmentDirections
import com.example.todolist.viewmodel.NotesViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesListView(
    notes: List<Note>,
    viewModel: NotesViewModel,
    navController: NavController
) {
    Column(Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            userScrollEnabled = true
        ) {
            notes.forEach { note ->
                item {
                    NotesListItem(navController = navController, note = note, viewModel = viewModel)
                }
            }
        }
    }
}



