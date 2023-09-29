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

interface INoteService{
    fun createNote(noteRequest: NoteRequest): Boolean
    fun findAllNotes(): Set<Note>
    fun findNoteById(noteId: Int): Note?
    fun updateNoteById(noteId: Int, noteRequest: NoteRequest): Boolean
    fun deleteNoteById(noteId: Int): Boolean
}

class NoteService: INoteService{
    private val database = Database.connect (
        url= "jdbc:postgresql://localhost:5432/qa_notes",
        driver = "org.postgresql.Driver",
        user = "qa_notes",
        password = "admin"
    )

    override fun createNote(noteRequest: NoteRequest): Boolean{
        val newNote = Note{
            title = noteRequest.title
            body = noteRequest.body
        }

        val affectedRecordsNumber = database.sequenceOf(Notes).add(newNote)

        return affectedRecordsNumber == 1
    }

    override fun findAllNotes(): Set<Note> =
        database.sequenceOf(Notes).toSet()


    override fun findNoteById(noteId: Int): Note? =
        database.sequenceOf(Notes).find { notes -> notes.id eq  noteId }

    override fun updateNoteById(noteId: Int, noteRequest: NoteRequest): Boolean{
        val foundNote = findNoteById(noteId)
        foundNote?.title = noteRequest.title
        foundNote?.body = noteRequest.body

        val affectedRecordsNumber = foundNote?.flushChanges()

        return affectedRecordsNumber == 1
    }

    override fun deleteNoteById(noteId: Int): Boolean{
        val foundNote = findNoteById(noteId)

        val affectedRecordsNumber = foundNote?.delete()

        return affectedRecordsNumber == 1
    }

}


class MockNoteService: INoteService{
    override fun createNote(noteRequest: NoteRequest): Boolean {
        return true
    }

    override fun findAllNotes(): Set<Note> {
        return setOf(
            Note{id = 1; title = "note1"; body = "body1"},
            Note{id = 2; title = "note2"; body = "body2"},
            Note{id = 3; title = "note3"; body = "body3"},
            Note{id = 4; title = "note4"; body = "body4"},
            Note{id = 5; title = "note5"; body = "body5"},
            Note{id = 6; title = "note6"; body = "body6"},
        )

    }

    override fun findNoteById(noteId: Int): Note? {
        return when(noteId){
            1 -> Note{id = 1; title = "note1"; body = "body1"}
            2 -> Note{id = 2; title = "note2"; body = "body2"}
            3 -> Note{id = 3; title = "note3"; body = "body3"}
            4 -> Note{id = 4; title = "note4"; body = "body4"}
            5 -> Note{id = 5; title = "note5"; body = "body5"}
            6 -> Note{id = 6; title = "note6"; body = "body6"}
            else -> null
        }
    }

    override fun updateNoteById(noteId: Int, noteRequest: NoteRequest): Boolean {
        return when(noteId){
            1 -> true
            2 -> true
            3 -> true
            4 -> true
            5 -> true
            6 -> true
            else -> false
        }
    }

    override fun deleteNoteById(noteId: Int): Boolean {
        return when(noteId){
            1 -> true
            2 -> true
            3 -> true
            4 -> true
            5 -> true
            6 -> true
            else -> false
        }
    }

}