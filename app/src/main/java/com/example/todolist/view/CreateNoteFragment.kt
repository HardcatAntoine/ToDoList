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
import com.example.todolist.databinding.FragmentCreateNoteBinding
import com.example.todolist.viewmodel.CreateNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    private val viewModel: CreateNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val doneBtn = binding.topAppBar.menu.findItem(R.id.done)
        binding.topAppBar.menu.findItem(R.id.share).isVisible = false
        binding.noteText.addTextChangedListener { text ->
            doneBtn.isVisible =
                binding.titleTv.text.toString().isNotEmpty() || text.toString().isNotEmpty()
        }
        binding.titleTv.addTextChangedListener { text ->
            doneBtn.isVisible =
                binding.noteText.text.toString().isNotEmpty() || text.toString().isNotEmpty()

        }
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.done -> {
                    val title = binding.titleTv.text.toString()
                    val noteText = binding.noteText.text.toString()
                    viewModel.insertNote(title, noteText)
                    findNavController().navigate(R.id.action_createNoteFragment_to_mainFragment)
                    true
                }

                else -> false
            }
        }
        binding.noteText.requestFocus()
        binding.noteText.requestFocusFromTouch()
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}

