package com.example.todolist.view

import android.view.View
import android.widget.PopupMenu
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.data.local.Note
import com.example.todolist.databinding.ItemNoteBinding

class NotesViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
    private val composeView = itemView as ComposeView

    @OptIn(ExperimentalFoundationApi::class)
    fun bind(item: Note, clickListener: ItemActionListener) {
        composeView.setContent {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = { clickListener.onItemClick(item) },
                        onLongClick = { clickListener.onItemLongClick(item, composeView) }
                    ),
                elevation = 0.dp,
                shape = RoundedCornerShape(10.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight(550),
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Normal
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = item.note
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = item.time,
                        fontSize = 10.sp
                    )

                }
            }

        }
    }
}



