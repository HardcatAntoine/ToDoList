package com.example.todolist.view.compose_components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
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
import com.example.todolist.R

@Composable
fun AddNoteButton( onAddButtonClick: () -> Unit) {
        FloatingActionButton(
            onClick = { onAddButtonClick() },
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