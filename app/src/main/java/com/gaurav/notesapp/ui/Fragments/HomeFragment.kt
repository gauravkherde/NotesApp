package com.gaurav.notesapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.gaurav.notesapp.R
import com.gaurav.notesapp.ViewModel.NotesViewModel
import com.gaurav.notesapp.databinding.FragmentHomeBinding
import com.gaurav.notesapp.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewmodel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)

        viewmodel.getNotes().observe(viewLifecycleOwner,{notesList->

            binding.recyclerViewAllNotes.layoutManager=GridLayoutManager(requireContext(),2)
            binding.recyclerViewAllNotes.adapter =NotesAdapter(requireContext(),notesList)
        })

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }


}