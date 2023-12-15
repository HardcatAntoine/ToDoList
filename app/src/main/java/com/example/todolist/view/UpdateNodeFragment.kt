package com.example.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.titleText.setText(args.node.title)
        binding.nodeText.setText(args.node.noteText)
        binding.topAppBar.menu.findItem(R.id.done).isVisible = true
        binding.topAppBar.menu.findItem(R.id.top_app_bar_menu_delete).isVisible = true
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.done -> {
                    updateNode()
                    true
                }

                R.id.top_app_bar_menu_delete -> {
                    viewModel.removeNode(args.node)
                    findNavController().navigateUp()
                    true
                }

                else -> false
            }

        }
        binding.topAppBar.setNavigationOnClickListener {
            updateNode()
        }
    }

    private fun updateNode() {
        val currentTime = SimpleDateFormat(
            "dd.MM.yyyy HH:mm",
            Locale.getDefault()
        ).format(Calendar.getInstance().time)
        val node = Nodes(
            args.node.id,
            title = binding.titleText.text.toString(),
            noteText = binding.nodeText.text.toString(),
            time = currentTime
        )
        viewModel.updateNode(node)
        findNavController().navigate(R.id.action_updateNodeFragment_to_mainFragment)
    }
}