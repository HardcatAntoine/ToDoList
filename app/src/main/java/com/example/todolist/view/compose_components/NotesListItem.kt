package com.example.todolist.view.compose_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import com.example.todolist.data.local.Note
import com.example.todolist.view.MainFragmentDirections
import com.example.todolist.viewmodel.NotesViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesListItem(
    navController: NavController,
    note: Note,
    viewModel: NotesViewModel
){
    var showMenu by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 6.dp)
            .combinedClickable(
                onClick = {
                    navController.navigate(
                        MainFragmentDirections.actionMainFragmentToUpdateNoteFragment(
                            note
                        )
                    )
                },
                onLongClick = { showMenu = true }
            ),
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),

        ) {
        if (showMenu) {
            DropDownMenu(
                note = note,
                onDeleteClick = {
                    viewModel.removeNote(note)
                    showMenu = false
                },
                onShowMenu = { showMenu }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
        ) {
            if (note.title.isNotEmpty()) {
                Text(
                    text = note.title,
                    fontWeight = FontWeight(550),
                    fontSize = 20.sp,
                    maxLines = 3
                )
            }
            if (note.note.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = note.note,
                    maxLines = 3
                )
            }
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = note.time,
                fontSize = 10.sp,
            )
        }

    }
}