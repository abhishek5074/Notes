package com.github.abhishek5074.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.abhishek5074.notes.MainActivity
import com.github.abhishek5074.notes.R
import com.github.abhishek5074.notes.adapter.NoteAdapter
import com.github.abhishek5074.notes.databinding.FragmentHomeBinding
import com.github.abhishek5074.notes.viewmodel.NoteViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)
        notesViewModel = (activity as MainActivity).noteViewModel
            notesViewModel.getAllNote().observe(viewLifecycleOwner, { note ->
                noteAdapter = NoteAdapter(note)
                setUpRecyclerView()
            })

        binding.fabAddNote.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
        return binding.root
    }

    private fun setUpRecyclerView() {
            binding.recyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
                setHasFixedSize(true)
                adapter = noteAdapter
            }
    }

}