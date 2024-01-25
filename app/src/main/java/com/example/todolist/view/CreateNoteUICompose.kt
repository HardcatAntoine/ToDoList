package com.example.todolist.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.todolist.viewmodel.CreateNoteViewModel

@Composable
fun CreateNoteUICompose(
    onBackClick: () -> Unit,
    viewModel: CreateNoteViewModel
) {
    var showDoneBtn by remember { mutableStateOf(false) }
    var titleText by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = "Create note") },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = "back"
                    )
                }
            },
            actions = {
                if (showDoneBtn) {
                    IconButton(onClick = {
                        viewModel.insertNote(titleText, noteText)
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
            value = titleText,
            onValueChange = {
                titleText = it
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
                showDoneBtn = true
            },
            label = { Text(text = "Note text") }
        )
        if (titleText == "" && noteText == "") showDoneBtn = false
    }
}