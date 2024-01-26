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
import com.example.todolist.viewmodel.UpdateNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteFragment : Fragment() {
    private lateinit var composeView: ComposeView
    private val viewModel: UpdateNoteViewModel by viewModels()
    private val args: UpdateNoteFragmentArgs by lazy {
        UpdateNoteFragmentArgs.fromBundle(requireArguments())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView = view.findViewById(R.id.composeViewUpdateFragmentUI)
        composeView.setContent {
            UpdateFragmentUICompose(
                note = args.note,
                viewModel = viewModel,
                onBackClick = { findNavController().navigateUp() },
            )
        }
    }
}