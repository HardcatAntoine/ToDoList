package com.example.todolist.view.composecomponents

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.data.local.Note

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesListView(
    notes: List<Note>,
    onDeleteClick: (Note) -> Unit,
    onItemClick: (Note) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = "Notebook") })
        LazyColumn(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            userScrollEnabled = true
        ) {
            notes.forEach {
                item {
                    var showMenu by remember { mutableStateOf(false) }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 6.dp)
                            .combinedClickable(
                                onClick = { onItemClick(it) },
                                onLongClick = { showMenu = true }
                            ),
                        elevation = 3.dp,
                        shape = RoundedCornerShape(10.dp),

                        ) {
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
                                            onDeleteClick(it)
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
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                        ) {
                            if (it.title.isNotEmpty()) {
                                Text(
                                    text = it.title,
                                    fontWeight = FontWeight(550),
                                    fontSize = 20.sp,
                                    maxLines = 3
                                )
                            }
                            if (it.note.isNotEmpty()) {
                                Text(
                                    modifier = Modifier.padding(top = 2.dp),
                                    text = it.note,
                                    maxLines = 3
                                )
                            }
                            Text(
                                modifier = Modifier.padding(top = 2.dp),
                                text = it.time,
                                fontSize = 10.sp,
                            )
                        }

                    }
                }
            }
        }
    }
}



