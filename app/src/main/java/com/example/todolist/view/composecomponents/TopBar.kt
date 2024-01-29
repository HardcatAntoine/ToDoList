package com.example.todolist.view.composecomponents

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TopBar(){
    TopAppBar(title = { Text(text = "Notebook") })
}