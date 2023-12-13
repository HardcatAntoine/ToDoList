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
import java.util.Calendar

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
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.createBtn.setOnClickListener {
            createNode()
        }
    }

    private fun createNode() {
        val name = binding.nodeNameTv.text.toString()
        val description = binding.descriptionTv.text.toString()
        val time = Calendar.getInstance().time.toString()
        if (name.isNullOrEmpty()) {
            showAlertDialog()
        } else {
            viewModel.insertNode(Nodes(name, description, time))
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
}