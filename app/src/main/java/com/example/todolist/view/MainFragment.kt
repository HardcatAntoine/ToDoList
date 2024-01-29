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
import com.example.todolist.view.composecomponents.MainScreen
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
            viewModel.listNote.observe(viewLifecycleOwner) { notes ->
                setContent {
                    MainScreen(notes = notes, viewModel = viewModel, navController = findNavController())
                }
            }
        }
    }
}





