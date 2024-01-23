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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.data.local.Note
import com.example.todolist.view.ItemActionListener

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesListView(notes: List<Note>, clickListener: ItemActionListener) {
    LazyColumn(
        modifier = Modifier.padding(8.dp),
        userScrollEnabled = true
    ) {
        notes.forEach {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .combinedClickable(
                            onClick = { clickListener.onItemClick(it) },
                         //    onLongClick = { clickListener.onItemLongClick(it,) }
                        ),
                    elevation = 0.dp,
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color.LightGray

                ) {
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