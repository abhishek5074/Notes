package com.github.abhishek5074.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.abhishek5074.notes.R
import com.github.abhishek5074.notes.ui.HomeFragmentDirections
import com.github.abhishek5074.notes.model.Note


class NoteAdapter(val note : List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val noteTitle : TextView = itemView.findViewById(R.id.tvNoteTitle)
            val noteBody : TextView = itemView.findViewById(R.id.tvNoteBody)
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_layout_adapter,parent,false)
        )

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = note[position]

        holder.noteTitle.text = currentNote.noteTitle
        holder.noteBody.text = currentNote.noteBody

        holder.itemView.setOnClickListener { view ->

            val direction = HomeFragmentDirections
                .actionHomeFragmentToUpdateNoteFragment(currentNote)
            view.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return note.size
    }

}