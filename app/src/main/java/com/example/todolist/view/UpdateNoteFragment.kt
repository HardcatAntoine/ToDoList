package com.example.todolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.compose.ui.platform.ComposeView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.databinding.FragmentUpdateNoteBinding
import com.example.todolist.viewmodel.UpdateNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteFragment : Fragment() {
    private lateinit var binding: FragmentUpdateNoteBinding
    private lateinit var titleText: EditText
    private lateinit var noteText: EditText
    private lateinit var doneBtn: MenuItem
    private lateinit var undoBtn: MenuItem
    private lateinit var redoBtn: MenuItem
    private lateinit var shareBtn: MenuItem
    private lateinit var menuBtn: MenuItem

    private lateinit var composeView: ComposeView
    private val viewModel: UpdateNoteViewModel by viewModels()
    private val args: UpdateNoteFragmentArgs by lazy {
        UpdateNoteFragmentArgs.fromBundle(requireArguments())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater)
//        titleText = binding.titleText
//        noteText = binding.noteText
//        undoBtn = binding.topAppBar.menu.findItem(R.id.undo)
//        redoBtn = binding.topAppBar.menu.findItem(R.id.redo)
//        doneBtn = binding.topAppBar.menu.findItem(R.id.done)
//        menuBtn = binding.topAppBar.menu.findItem(R.id.top_app_bar_menu_delete)
//        shareBtn = binding.topAppBar.menu.findItem(R.id.share)
        return binding.root
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

//        initObservers()
//        titleText.setText(args.note.title)
//        noteText.setText(args.note.note)
//        val noteTextChangeListener = noteText.addTextChangedListener { text ->
//            viewModel.updateDoneBtnVisibility(text.toString() != args.note.note)
//            viewModel.enableUndoBtn()
//            viewModel.noteChanged(text.toString())
//        }
//        val titleTextChangeListener = titleText.addTextChangedListener { text ->
//            viewModel.updateDoneBtnVisibility(text.toString() != args.note.title)
//            viewModel.enableUndoBtn()
//            viewModel.titleChanged(text.toString())
//        }
//        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.done -> {
//                    val title = titleText.text.toString()
//                    val noteText = noteText.text.toString()
//                    if (title.isEmpty() && noteText.isEmpty()) {
//                        viewModel.removeNote(args.note)
//                        findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment)
//                    } else {
//                        viewModel.updateNote(args.note.id, title, noteText)
//                        findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment)
//                    }
//                    true
//                }
//
//                R.id.top_app_bar_menu_delete -> {
//                    viewModel.removeNote(args.note)
//                    findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment)
//                    true
//                }
//
//                else -> false
//            }
//
//        }
//        binding.topAppBar.setNavigationOnClickListener {
//            findNavController().navigateUp()
//        }
//        undoBtn.setOnMenuItemClickListener {
//            titleText.removeTextChangedListener(titleTextChangeListener)
//            noteText.removeTextChangedListener(noteTextChangeListener)
//            viewModel.enableRedoBtn()
//            val previousNote = viewModel.undo()
//            titleText.setText(previousNote.title)
//            noteText.setText(previousNote.note)
//            titleText.addTextChangedListener(titleTextChangeListener)
//            noteText.addTextChangedListener(noteTextChangeListener)
//            true
//        }
//        redoBtn.setOnMenuItemClickListener {
//            titleText.removeTextChangedListener(titleTextChangeListener)
//            noteText.removeTextChangedListener(noteTextChangeListener)
//            viewModel.enableUndoBtn()
//            val nextNote = viewModel.redo()
//            titleText.setText(nextNote.title)
//            noteText.setText(nextNote.note)
//            titleText.addTextChangedListener(titleTextChangeListener)
//            noteText.addTextChangedListener(noteTextChangeListener)
//            true
//        }
//    }
//
//    private fun btnVisibility(doneBtnIsVisible: Boolean) {
//        if (doneBtnIsVisible) {
//            doneBtn.isVisible = true
//            undoBtn.isVisible = true
//            redoBtn.isVisible = true
//            shareBtn.isVisible = false
//            menuBtn.isVisible = false
//        } else {
//            shareBtn.isVisible = true
//            menuBtn.isVisible = true
//            undoBtn.isVisible = false
//            redoBtn.isVisible = false
//            doneBtn.isVisible = false
//        }
//    }
//
//    private fun initObservers() {
//        viewModel.doneBtnIsVisibleState.observe(viewLifecycleOwner) {
//            btnVisibility(it)
//        }
//        viewModel.isUndoBtnEnabled.observe(viewLifecycleOwner) {
//            undoBtn.isEnabled = it
//        }
//        viewModel.isRedoBtnEnabled.observe(viewLifecycleOwner) {
//            redoBtn.isEnabled = it
//        }
    }

}