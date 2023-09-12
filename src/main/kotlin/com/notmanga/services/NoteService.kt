package com.notmanga.services

import com.notmanga.dao.Note
import com.notmanga.dao.Notes
import com.notmanga.dto.NoteRequest
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toSet

class NoteService{
    private val database = Database.connect (
        url= "jdbc:postgresql://localhost:5432/qa_notes",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "admin"
    )

    fun createNote(noteRequest: NoteRequest): Boolean{
        val newNote = Note{
            title = noteRequest.title
            body = noteRequest.body
        }

        val affectedRecordsNumber = database.sequenceOf(Notes).add(newNote)

        return affectedRecordsNumber == 1
    }

    fun findAllNotes(): Set<Note> =
        database.sequenceOf(Notes).toSet()


    fun findNoteById(noteId: Int): Note? =
        database.sequenceOf(Notes).find { notes -> notes.id eq  noteId }

    fun updateNoteById(noteId: Int, noteRequest: NoteRequest): Boolean{
        val foundNote = findNoteById(noteId)
        foundNote?.title = noteRequest.title
        foundNote?.body = noteRequest.body

        val affectedRecordsNumber = foundNote?.flushChanges()

        return affectedRecordsNumber == 1
    }

    fun deleteNoteById(noteId: Int): Boolean{
        val foundNote = findNoteById(noteId)

        val affectedRecordsNumber = foundNote?.delete()

        return affectedRecordsNumber == 1
    }

}