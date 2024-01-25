package com.example.todolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.FloatingActionButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.data.local.Note
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.view.composecomponents.NotesListView
import com.example.todolist.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), ItemActionListener {
    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerCompose: ComposeView
    private lateinit var addBtn: ComposeView
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotesList()
        recyclerCompose = binding.rvNotes
        addBtn = binding.addBtn
        addBtn.setContent {
            FloatingActionButton(
                onClick = { findNavController().navigate(R.id.action_mainFragment_to_createNoteFragment) },
                shape = RoundedCornerShape(15.dp),
                elevation = FloatingActionButtonDefaults.elevation(10.dp),
                backgroundColor = MaterialTheme.colors.primarySurface
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "Add"
                )
            }
        }
        viewModel.listNote.observe(viewLifecycleOwner) { notes ->
            recyclerCompose.setContent {
                NotesListView(notes = notes, clickListener = this) {
                    viewModel.removeNote(it)
                }
            }
        }
    }

    override fun onItemClick(note: Note) {
        val action = MainFragmentDirections.actionMainFragmentToUpdateNoteFragment(note)
        findNavController().navigate(action)
    }
}





