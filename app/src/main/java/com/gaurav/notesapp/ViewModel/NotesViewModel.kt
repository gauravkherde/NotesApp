package com.gaurav.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gaurav.notesapp.Database.NotesDatabase
import com.gaurav.notesapp.Model.Notes
import com.gaurav.notesapp.Repository.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val repository : NotesRepository

    init {
        val dao= NotesDatabase.getDatabaseinstance(application).myNotesDao()
        repository=NotesRepository(dao)
    }

    fun addNotes(notes: Notes)
    {
        repository.insertNotes(notes)
    }

    fun getNotes():LiveData<List<Notes>> = repository.getallNotes()

    fun deleteNotes(id:Int)
    {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes)
    {
        repository.updateNotes(notes)
    }
}