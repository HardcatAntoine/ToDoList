package com.example.todolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.view.screens.MainScreen
import com.example.todolist.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getNotesList()
        return ComposeView(requireActivity()).apply {
            setContent {
                MainScreen(
                    notesList = viewModel.notes,
                    onItemClick = {
                        findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToUpdateNoteFragment(it))
                    },
                    onDeleteClick = { viewModel.removeNote(note = it) },
                    onAddButtonClick = { findNavController().navigate(R.id.action_mainFragment_to_createNoteFragment) }
                )
            }
        }
    }
}





