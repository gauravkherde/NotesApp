package com.gaurav.notesapp.Repository

import androidx.lifecycle.LiveData
import com.gaurav.notesapp.Dao.NotesDao
import com.gaurav.notesapp.Model.Notes

class NotesRepository(val dao :NotesDao) {

    fun getallNotes():LiveData<List<Notes>>{
        return dao.getNotes()
    }
    fun getlowNotes():LiveData<List<Notes>>{
        return dao.getLowNotes()
    }
    fun getmediumNotes():LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }
    fun gethighNotes():LiveData<List<Notes>>{
        return dao.getHighNotes()
    }


    fun insertNotes(notes: Notes)
    {
        dao.insertNotes(notes)
    }
    fun deleteNotes(id:Int)
    {
        dao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes)
    {
        dao.updateNotes(notes)
    }
}