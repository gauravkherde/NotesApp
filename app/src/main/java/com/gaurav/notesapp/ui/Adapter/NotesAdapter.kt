package com.gaurav.notesapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gaurav.notesapp.Model.Notes
import com.gaurav.notesapp.R
import com.gaurav.notesapp.databinding.ItemsNotesBinding

class NotesAdapter(val requireContext: Context,val  notesList: List<Notes>) :RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {
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
    }

    override fun getItemCount(): Int {
       return notesList.size
    }
}