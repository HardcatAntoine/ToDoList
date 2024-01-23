package com.example.todolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.data.local.Note
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.view.composecomponents.NotesListView
import com.example.todolist.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), ItemActionListener {
    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerCompose: ComposeView
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotesList()
        recyclerCompose = binding.rvNotes
        observers()
        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_createNoteFragment)
        }

    }

    private fun observers() {
        viewModel.listNote.observe(viewLifecycleOwner) { notes ->
            recyclerCompose.setContent {
                NotesListView(notes = notes, clickListener = this)
            }
        }
    }

    override fun onItemLongClick(note: Note, view: View) {

    }

    override fun onItemClick(note: Note) {
        val action = MainFragmentDirections.actionMainFragmentToUpdateNoteFragment(note)
        findNavController().navigate(action)
    }

//    private fun showPopUpMenu(note: Note, view: View) {
//        val popupMenu = PopupMenu(requireContext(), view)
//        popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
//        popupMenu.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.popup_menu_delete -> {
//                    viewModel.removeNote(note)
//                    true
//                }
//
//                else -> false
//            }
//
//        }
//        popupMenu.show()
//    }

}


