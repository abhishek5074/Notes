package com.github.abhishek5074.notes.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.github.abhishek5074.notes.MainActivity
import com.github.abhishek5074.notes.R
import com.github.abhishek5074.notes.databinding.FragmentNewNoteBinding
import com.github.abhishek5074.notes.model.Note
import com.github.abhishek5074.notes.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar


class NewNoteFragment : Fragment() {
    private lateinit var binding : FragmentNewNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_new_note,
            container,
            false
        )
        noteViewModel = (activity as MainActivity).noteViewModel

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun saveNote() {
        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteBody = binding.etNoteBody.text.toString().trim()

        if (noteTitle.isNotEmpty()) {
            val note = Note(0, noteTitle, noteBody)

            noteViewModel.addNote(note)
            Snackbar.make(
                requireView(), "Note saved successfully",
                Snackbar.LENGTH_SHORT
            ).show()
            requireView().findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)

        } else {
            Toast.makeText(context, "Please enter note title", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_new_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                saveNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}