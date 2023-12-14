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
import com.example.todolist.databinding.FragmentCreateNodeBinding
import com.example.todolist.viewmodel.CreateNodeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class CreateNodeFragment : Fragment() {
    lateinit var binding: FragmentCreateNodeBinding
    private val viewModel: CreateNodeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nodeText.onCheckIsTextEditor()
        binding.nodeText.isCursorVisible = true
        binding.topAppBar.setNavigationOnClickListener {
            showDialogForClickTopBarNav()
        }
        binding.createBtn.setOnClickListener {
            createNode()
        }
    }

    private fun createNode() {
        val title = binding.titleTv.text.toString()
        val nodeText = binding.nodeText.text.toString()
        val currentTime =
            SimpleDateFormat(
                "dd.MM.yyyy HH:mm",
                Locale.getDefault()
            ).format(Calendar.getInstance().time)
        if (title.isNullOrEmpty()) {
            showAlertDialog()
        } else {
            viewModel.insertNode(Nodes(title, nodeText, currentTime))
            findNavController().navigate(R.id.action_createNodeFragment_to_mainFragment)
        }

    }

    private fun showAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage("Input name for node")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun showDialogForClickTopBarNav() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Save changes?")
            .setMessage("Save changes?")
            .setNeutralButton("No") { dialog, _ ->
                dialog.cancel()
                findNavController().navigateUp()
            }.setPositiveButton("Yes") { dialog, _ ->
                createNode()
            }
            .show()


    }
}