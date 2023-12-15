package com.example.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.data.local.Nodes
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.viewmodel.NodesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), ItemActionListener {
    lateinit var binding: FragmentMainBinding
    private val adapter = NodesAdapter()
    private val viewModel: NodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.getNodesList()
        observers()
        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_createNodeFragment)
        }

    }

    private fun initAdapter() {
        binding.rvNodes.adapter = adapter
        adapter.setItemActionListener(this)
        binding.rvNodes.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observers() {
        viewModel.listNodes.observe(viewLifecycleOwner) { nodes ->
            adapter.setList(nodes)
        }
    }

    override fun onItemLongClick(node: Nodes, view: View) {
        showPopUpMenu(node, view)
    }

    override fun onItemClick(node: Nodes) {
        val action = MainFragmentDirections.actionMainFragmentToUpdateNodeFragment(node)
        findNavController().navigate(action)
    }

    private fun showPopUpMenu(node: Nodes, view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.popup_menu_delete -> {
                    viewModel.removeNode(node)
                    true
                }

                else -> false
            }

        }
        popupMenu.show()
    }

}