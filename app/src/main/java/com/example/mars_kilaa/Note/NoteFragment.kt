package com.example.mars_kilaa.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mars_kilaa.R
import com.example.mars_kilaa.data.AppDatabase
import com.example.mars_kilaa.data.entity.NoteEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Notes"

        // Inisialisasi AppDatabase & Adapter
        db = AppDatabase.getInstance(requireContext())
        adapter = NoteAdapter(notes, this)

        val rvNotes = view.findViewById<RecyclerView>(R.id.rvNotes)
        rvNotes.layoutManager = LinearLayoutManager(requireContext())
        rvNotes.adapter = adapter

        // Tambah garis pemisah antar item
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        rvNotes.addItemDecoration(dividerItemDecoration)

        fetchNotes()

        // Tombol tambah catatan
        val fabAddNote = view.findViewById<FloatingActionButton>(R.id.fabAddNote)
        fabAddNote.setOnClickListener {
            val intent = Intent(requireContext(), NoteFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchNotes() {
        viewLifecycleOwner.lifecycleScope.launch {
            val data = db.noteDao().getAll()
            notes.clear()
            notes.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            db.noteDao().delete(note)
            fetchNotes()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchNotes()
    }
}