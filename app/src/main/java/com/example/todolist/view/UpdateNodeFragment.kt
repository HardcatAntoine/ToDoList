package com.example.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.data.local.Nodes
import com.example.todolist.databinding.FragmentUpdateNodeBinding
import com.example.todolist.viewmodel.UpdateNodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class UpdateNodeFragment : Fragment() {
    lateinit var binding: FragmentUpdateNodeBinding
    private val viewModel: UpdateNodeViewModel by viewModels()
    private val args: UpdateNodeFragmentArgs by lazy {
        UpdateNodeFragmentArgs.fromBundle(requireArguments())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateNodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val doneBtn = binding.topAppBar.menu.findItem(R.id.done)
        val menuBtn = binding.topAppBar.menu.findItem(R.id.top_app_bar_menu_delete)
        val shareBtn = binding.topAppBar.menu.findItem(R.id.share)
        val undoBtn = binding.topAppBar.menu.findItem(R.id.undo)
        val redoBtn = binding.topAppBar.menu.findItem(R.id.redo)
        binding.titleText.setText(args.node.title)
        binding.nodeText.setText(args.node.noteText)
        shareBtn.isVisible = true
        menuBtn.isVisible = true
        binding.nodeText.addTextChangedListener { text ->
            doneBtn.isVisible = text.toString() != args.node.noteText
            if (doneBtn.isVisible) {
                undoBtn.isVisible = true
                redoBtn.isVisible = true
                shareBtn.isVisible = false
                menuBtn.isVisible = false
            } else {
                shareBtn.isVisible = true
                menuBtn.isVisible = true
                undoBtn.isVisible = false
                redoBtn.isVisible = false
            }
        }
        binding.titleText.addTextChangedListener { text ->
            doneBtn.isVisible = text.toString() != args.node.title
            if (doneBtn.isVisible) {
                undoBtn.isVisible = true
                redoBtn.isVisible = true
                shareBtn.isVisible = false
                menuBtn.isVisible = false
            } else {
                shareBtn.isVisible = true
                menuBtn.isVisible = true
                undoBtn.isVisible = false
                redoBtn.isVisible = false
            }
        }
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.done -> {
                    val title = binding.titleText.text.toString()
                    val noteText = binding.nodeText.text.toString()
                    if (title.isEmpty() && noteText.isEmpty()) {
                        viewModel.removeNode(args.node)
                        findNavController().navigate(R.id.action_updateNodeFragment_to_mainFragment)
                    } else {
                        viewModel.updateNode(args.node.id, title, noteText)
                        findNavController().navigate(R.id.action_updateNodeFragment_to_mainFragment)
                    }
                    true
                }

                R.id.top_app_bar_menu_delete -> {
                    viewModel.removeNode(args.node)
                    findNavController().navigate(R.id.action_updateNodeFragment_to_mainFragment)
                    true
                }

                else -> false
            }

        }
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }


}