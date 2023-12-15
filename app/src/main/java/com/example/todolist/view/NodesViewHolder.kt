package com.example.todolist.view

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist.R
import com.example.todolist.data.local.Nodes
import com.example.todolist.databinding.ItemNodeBinding

class NodesViewHolder(private val binding: ItemNodeBinding) : ViewHolder(binding.root) {
    fun bind(item: Nodes, clickListener: ClickListeners) {
        binding.timeCreated.text = item.time
        if (item.description.isEmpty()) {
            binding.nodeText.visibility = View.GONE
        } else {
            binding.nodeText.text = item.description
        }
        if (item.name.isEmpty()) {
            binding.titleText.visibility = View.GONE
        } else {
            binding.titleText.text = item.name
        }
        itemView.setOnClickListener {
            clickListener.onItemClickListener(item)
        }
        itemView.setOnLongClickListener {
            val popupMenu = PopupMenu(itemView.context, itemView)
            popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                clickListener.removeNode(item)
                true
            }
            popupMenu.show()
            true

        }


    }

}


