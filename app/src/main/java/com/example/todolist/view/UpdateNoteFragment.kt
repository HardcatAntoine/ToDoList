package com.example.todolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val viewModel: UpdateNoteViewModel by viewModels()
    private val args: UpdateNoteFragmentArgs by lazy {
        UpdateNoteFragmentArgs.fromBundle(requireArguments())
    }

    private var doneBtnIsVisibleState = false
    private var isUndoBtnEnabled = false
    private var isRedoBtnEnabled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val undoBtn = binding.topAppBar.menu.findItem(R.id.undo)
        val redoBtn = binding.topAppBar.menu.findItem(R.id.redo)
        val changeTextBuffer = mutableListOf(args.note)
        var currentTextBufferIndex = 1
        binding.titleText.setText(args.note.title)
        binding.noteText.setText(args.note.note)
        val noteTextChangeListener = binding.noteText.addTextChangedListener { text ->
            doneBtnIsVisibleState = text.toString() != args.note.note
            btnVisibility(doneBtnIsVisibleState)
            if (doneBtnIsVisibleState) {
                isUndoBtnEnabled = true
                undoBtn.isEnabled = isUndoBtnEnabled
            }
            val lastChange = changeTextBuffer.last()
            val newChange = lastChange.copy(note = text.toString())
            changeTextBuffer.add(newChange)
        }
        val titleTextChangeListener = binding.titleText.addTextChangedListener { text ->
            doneBtnIsVisibleState = text.toString() != args.note.title
            btnVisibility(doneBtnIsVisibleState)
            if (doneBtnIsVisibleState) {
                isUndoBtnEnabled = true
                undoBtn.isEnabled = isUndoBtnEnabled
            }
            val lastChange = changeTextBuffer.last()
            val newChange = lastChange.copy(title = text.toString())
            changeTextBuffer.add(newChange)
        }
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.done -> {
                    val title = binding.titleText.text.toString()
                    val noteText = binding.noteText.text.toString()
                    if (title.isEmpty() && noteText.isEmpty()) {
                        viewModel.removeNote(args.note)
                        findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment)
                    } else {
                        viewModel.updateNote(args.note.id, title, noteText)
                        findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment)
                    }
                    true
                }

                R.id.top_app_bar_menu_delete -> {
                    viewModel.removeNote(args.note)
                    findNavController().navigate(R.id.action_updateNoteFragment_to_mainFragment)
                    true
                }

                else -> false
            }

        }
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        undoBtn.setOnMenuItemClickListener {
            binding.titleText.removeTextChangedListener(titleTextChangeListener)
            binding.noteText.removeTextChangedListener(noteTextChangeListener)
            isRedoBtnEnabled = true
            redoBtn.isEnabled = isRedoBtnEnabled
            if (currentTextBufferIndex < changeTextBuffer.size) {
                currentTextBufferIndex += 1
                val currentNote = changeTextBuffer[changeTextBuffer.size - currentTextBufferIndex]
                binding.titleText.setText(currentNote.title)
                binding.noteText.setText(currentNote.note)
            } else {
                if (currentTextBufferIndex == changeTextBuffer.size) {
                    isUndoBtnEnabled = false
                    undoBtn.isEnabled = isUndoBtnEnabled
                }
            }
            binding.titleText.addTextChangedListener(titleTextChangeListener)
            binding.noteText.addTextChangedListener(noteTextChangeListener)
            true
        }
        redoBtn.setOnMenuItemClickListener {
            binding.titleText.removeTextChangedListener(titleTextChangeListener)
            binding.noteText.removeTextChangedListener(noteTextChangeListener)
            isUndoBtnEnabled = true
            undoBtn.isEnabled = isUndoBtnEnabled
            if (currentTextBufferIndex > 1) {
                currentTextBufferIndex -= 1
                val currentNote = changeTextBuffer[changeTextBuffer.size - currentTextBufferIndex]
                binding.titleText.setText(currentNote.title)
                binding.noteText.setText(currentNote.note)
            } else {
                if (currentTextBufferIndex == 1) {
                    isRedoBtnEnabled = false
                    redoBtn.isEnabled = isRedoBtnEnabled
                    isUndoBtnEnabled = true
                    undoBtn.isEnabled = isUndoBtnEnabled
                }
            }
            binding.titleText.addTextChangedListener(titleTextChangeListener)
            binding.noteText.addTextChangedListener(noteTextChangeListener)
            true
        }
    }

    private fun btnVisibility(doneBtnIsVisible: Boolean) {
        val doneBtn = binding.topAppBar.menu.findItem(R.id.done)
        val menuBtn = binding.topAppBar.menu.findItem(R.id.top_app_bar_menu_delete)
        val shareBtn = binding.topAppBar.menu.findItem(R.id.share)
        val undoBtn = binding.topAppBar.menu.findItem(R.id.undo)
        val redoBtn = binding.topAppBar.menu.findItem(R.id.redo)
        if (doneBtnIsVisible) {
            doneBtn.isVisible = true
            undoBtn.isVisible = true
            redoBtn.isVisible = true
            shareBtn.isVisible = false
            menuBtn.isVisible = false
        } else {
            shareBtn.isVisible = true
            menuBtn.isVisible = true
            undoBtn.isVisible = false
            redoBtn.isVisible = false
            doneBtn.isVisible = false
        }
    }

}