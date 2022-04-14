package com.gaurav.notesapp.ui.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gaurav.notesapp.Model.Notes
import com.gaurav.notesapp.R
import com.gaurav.notesapp.ViewModel.NotesViewModel
import com.gaurav.notesapp.databinding.FragmentHomeBinding
import com.gaurav.notesapp.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewmodel: NotesViewModel by viewModels()
    var oldmynotes= arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        //layout manager
            val staggerviewlayoutmanger= StaggeredGridLayoutManager(2,LinearLayout.VERTICAL)
            binding.recyclerViewAllNotes.layoutManager= staggerviewlayoutmanger


        // allnotes
        viewmodel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldmynotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.recyclerViewAllNotes.adapter = adapter
        }

        // high notes
        binding.filterhigh.setOnClickListener {
            viewmodel.gethighNotes().observe(viewLifecycleOwner) { notesList ->
                oldmynotes=notesList as ArrayList<Notes>
                adapter= NotesAdapter(requireContext(), notesList)
                binding.recyclerViewAllNotes.adapter = adapter
            }
        }

        // low notes

        binding.filterlow.setOnClickListener {
            viewmodel.getlowNotes().observe(viewLifecycleOwner) { notesList ->
                oldmynotes=notesList as ArrayList<Notes>
                adapter= NotesAdapter(requireContext(), notesList)
                binding.recyclerViewAllNotes.adapter = adapter
            }
        }

        // medium notes

        binding.filtermedium.setOnClickListener {
            viewmodel.getmediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldmynotes=notesList as ArrayList<Notes>
                adapter= NotesAdapter(requireContext(), notesList)
                binding.recyclerViewAllNotes.adapter = adapter
            }
        }

        // filer all notes

        binding.filterallnotes.setOnClickListener {
            viewmodel.getmediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldmynotes=notesList as ArrayList<Notes>
                adapter= NotesAdapter(requireContext(), notesList)
                binding.recyclerViewAllNotes.adapter = adapter
            }
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item=menu.findItem(R.id.app_bar_search)
        val seacrhView=item.actionView as SearchView
        seacrhView.queryHint="Enter notes here..."
        seacrhView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                NotesFiltering(p0)
                return  true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private  fun  NotesFiltering(p0: String?){

        val newfilterlist = arrayListOf<Notes>()
        for (i in oldmynotes){
            if (i.title.contains(p0!!)|| i.subtitle.contains(p0!!))
            {
                newfilterlist.add(i)
            }
        }
        adapter.filtering(newfilterlist)
    }
}