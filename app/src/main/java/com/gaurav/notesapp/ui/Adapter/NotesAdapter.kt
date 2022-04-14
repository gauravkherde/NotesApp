package com.gaurav.notesapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gaurav.notesapp.Model.Notes
import com.gaurav.notesapp.R
import com.gaurav.notesapp.databinding.ItemsNotesBinding
import com.gaurav.notesapp.ui.Fragments.HomeFragment
import com.gaurav.notesapp.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context,var  notesList: List<Notes>) :RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filtering(newfilterlist: ArrayList<Notes>) {
        notesList=newfilterlist
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding: ItemsNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return  notesViewHolder(ItemsNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data =notesList[position]
        holder.binding.notestitle.text=data.title
        holder.binding.notessubtitle.text=data.subtitle
        holder.binding.notesdate.text=data.date

        when(data.priority)
        {
            "1"->{
                holder.binding.viewpriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewpriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.viewpriority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener{

            val action=HomeFragmentDirections.actionHomeFragmentToEditNotesFragments(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return notesList.size
    }
}