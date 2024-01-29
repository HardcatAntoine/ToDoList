package com.example.todolist.view.composecomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.R
import com.example.todolist.data.local.Note
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
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            onClick = { navController.navigate(R.id.action_mainFragment_to_createNoteFragment) },
            shape = RoundedCornerShape(15.dp),
            elevation = FloatingActionButtonDefaults.elevation(10.dp),
            backgroundColor = MaterialTheme.colors.primarySurface
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_icon),
                contentDescription = "Add"
            )
        }
    }

}



