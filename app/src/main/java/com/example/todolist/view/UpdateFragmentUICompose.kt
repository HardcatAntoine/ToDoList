package com.example.todolist.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.data.local.Note
import com.example.todolist.viewmodel.UpdateNoteViewModel

@Composable
fun UpdateFragmentUICompose(
    note: Note,
    viewModel: UpdateNoteViewModel,
    onBackClick: () -> Unit,
//    onUndoClick: () -> Unit,
//    onRedoClick: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        var showDoneBtn by remember { mutableStateOf(false) }
        var showMenu by remember { mutableStateOf(false) }
        var enableUndoBtn by remember { mutableStateOf(false) }
        var enableRedoBtn by remember { mutableStateOf(false) }
        var title by remember { mutableStateOf(note.title) }
        var noteText by remember { mutableStateOf(note.note) }
        TopAppBar(title = { Text(text = "Your note") },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = "back"
                    )
                }
            },
            actions = {
                if (!showDoneBtn) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.share_icon),
                            contentDescription = "share"
                        )
                    }
                    IconButton(onClick = { showMenu = true }) {
                        if (showMenu) {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { showMenu = false },
                            ) {
                                Card(
                                    shape = RoundedCornerShape(5.dp),
                                    elevation = 3.dp
                                ) {
                                    Column {
                                        DropdownMenuItem(onClick = {
                                            viewModel.removeNote(note)
                                            onBackClick()
                                            showMenu = false
                                            Log.d("MENU CLICK", "Delete click")
                                        }) {
                                            Row {
                                                Icon(
                                                    modifier = Modifier.padding(
                                                        start = 2.dp,
                                                        end = 3.dp
                                                    ),
                                                    painter = painterResource(id = R.drawable.delete_icon),
                                                    contentDescription = "delete",

                                                    )
                                                Text(text = "Delete")
                                            }
                                        }
                                        DropdownMenuItem(
                                            onClick = {
                                                showMenu = false
                                                Log.d("MENU CLICK", "Share click")
                                            },
                                            modifier = Modifier,
                                        ) {
                                            Row {
                                                Icon(
                                                    modifier = Modifier.padding(
                                                        start = 2.dp,
                                                        end = 3.dp
                                                    ),
                                                    painter = painterResource(id = R.drawable.share_icon),
                                                    contentDescription = "share"
                                                )
                                                Text(text = "Share")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more),
                            contentDescription = "more"
                        )
                    }

                } else {
                    IconButton(onClick = {
                        if (enableUndoBtn) enableRedoBtn = true
                    }) {
                        Icon(
                            painter = if (enableUndoBtn) {
                                painterResource(id = R.drawable.undo_icon_enabled)
                            } else {
                                painterResource(id = R.drawable.undo_icon_disabled)
                            }, contentDescription = "undo"
                        )
                    }
                    IconButton(onClick = {
                        if (enableRedoBtn) enableUndoBtn = true
                    }) {
                        Icon(
                            painter = if (enableRedoBtn) {
                                painterResource(id = R.drawable.redo_icon_enabled)
                            } else {
                                painterResource(id = R.drawable.redo_icon_disabled)
                            }, contentDescription = "redo"
                        )
                    }
                    IconButton(onClick = {
                        viewModel.updateNote(note.id, title, noteText)
                        onBackClick()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.done_icon),
                            contentDescription = "done"
                        )
                    }
                }
            })
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 8.dp, end = 8.dp),
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(500)),
            value = title,
            onValueChange = {
                title = it
                viewModel.titleChanged(it)
                enableUndoBtn = true
                showDoneBtn = true
            },
            label = { Text(text = "Title") })
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp),
            value = noteText,
            onValueChange = {
                noteText = it
                viewModel.noteChanged(it)
                enableUndoBtn = true
                showDoneBtn = true
            },
            label = { Text(text = "Note text") }
        )
    }
}