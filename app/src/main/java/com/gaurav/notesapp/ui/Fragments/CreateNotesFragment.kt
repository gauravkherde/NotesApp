package com.gaurav.notesapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.gaurav.notesapp.Model.Notes
import com.gaurav.notesapp.R
import com.gaurav.notesapp.ViewModel.NotesViewModel
import com.gaurav.notesapp.databinding.FragmentCreateNotesBinding
import java.util.*


class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"
    val viewmodel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)



        binding.pGreen.setOnClickListener {
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            priority = "1"

        }
        binding.pYellow.setOnClickListener {
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
            priority = "2"

        }
        binding.pRed.setOnClickListener {
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
            priority = "3"

        }



        binding.BtnSaveNote.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {

        val title = binding.EdtTitle.text.toString()
        val subtitle = binding.EdtSubtitle.text.toString()
        val notes = binding.EdtNotes.text.toString()
        val d = Date()
        val notesdate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        val data = Notes(
            null,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = notesdate.toString(),
            priority
        )
        viewmodel.addNotes(data)
        Toast.makeText(requireContext(), "Notes  created successfully ", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)


    }


}