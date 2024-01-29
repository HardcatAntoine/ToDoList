package com.example.todolist.view.compose_components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.data.local.Note

@Composable
fun DropDownMenu(
    note: Note,
    onDeleteClick: (Note) -> Unit,
    onShowMenu: (Boolean) -> Unit
) {
    DropdownMenu(
        expanded = true,
        onDismissRequest = { onShowMenu(false) },
    ) {
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 3.dp
        ) {
            Column {
                DropdownMenuItem(
                    onClick = {
                        onDeleteClick(note)
                        onShowMenu(false)
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
                        onShowMenu(false)
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