package com.gaurav.notesapp.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gaurav.notesapp.Dao.NotesDao

abstract class NotesDatabase :RoomDatabase() {
    abstract fun myNotesDao(): NotesDao

    companion object
    {
        @Volatile
        var INSTANCE: NotesDatabase?=null

        fun getDatabaseinstance(context: Context):NotesDatabase{

            val  tempInstance = INSTANCE

            if(tempInstance!=null)
            {
                return  tempInstance
            }
            synchronized(this)
            {
                val  roomDatabaseInstance=Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").build()

                INSTANCE=roomDatabaseInstance

                return roomDatabaseInstance
            }
        }
    }
}