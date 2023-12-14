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
import java.sql.Timestamp
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
        binding.topAppBar.title = args.node.name
        binding.nodeText.setText(args.node.description)
        binding.updateBtn.setOnClickListener {
           updateNode()
        }
        binding.topAppBar.setNavigationOnClickListener {
            updateNode()
        }
    }
    fun updateNode(){
        val currentTime =SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Calendar.getInstance().time)
        val node = Nodes(
            name = args.node.name,
            description = binding.nodeText.text.toString(),
            time = currentTime
        )
        viewModel.updateNode(node)
        findNavController().navigate(R.id.action_updateNodeFragment_to_mainFragment)
    }
}