package com.gaurav.notesapp.ui.Fragments

import android.os.Binder
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.gaurav.notesapp.Model.Notes
import com.gaurav.notesapp.R
import com.gaurav.notesapp.ViewModel.NotesViewModel
import com.gaurav.notesapp.databinding.FragmentEditNotesFragmentsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class EditNotesFragments : Fragment() {

    val Oldnotes by navArgs<EditNotesFragmentsArgs>()
    lateinit var binding: FragmentEditNotesFragmentsBinding
    var priority: String = "1"
    val viewmodel: NotesViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesFragmentsBinding.inflate(layoutInflater, container, false)
        binding.EdtTitle.setText(Oldnotes.data.title)
        binding.EdtSubtitle.setText(Oldnotes.data.subtitle)
        binding.EdtTitle.setText(Oldnotes.data.notes)
        setHasOptionsMenu(true)
        when(Oldnotes.data.priority)
        {
            "1"->{
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2"->{
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3"->{
                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }



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

        binding.BtnEditSaveNote.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.EdtTitle.text.toString()
        val subtitle = binding.EdtSubtitle.text.toString()
        val notes = binding.EdtNotes.text.toString()
        val d = Date()
        val notesdate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        val data = Notes(Oldnotes.data.id,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = notesdate.toString(),
            priority
        )
        viewmodel.updateNotes(data)
        Toast.makeText(requireContext(), "Notes updated successfully ", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragments_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete)
        {
            val bottonSheet:BottomSheetDialog= BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottonSheet.setContentView(R.layout.dialog_delete)
            val textviewYes=bottonSheet.findViewById<TextView>(R.id.dialog_yes)
            val textviewNo=bottonSheet.findViewById<TextView>(R.id.dialog_no)
            textviewYes?.setOnClickListener {
                viewmodel.deleteNotes(Oldnotes.data.id!!)
                bottonSheet.dismiss()
                //Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragments_to_homeFragment)

            }
            textviewYes?.setOnClickListener {
                bottonSheet.dismiss()
            }

            bottonSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }

}