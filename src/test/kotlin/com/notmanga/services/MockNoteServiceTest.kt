package com.notmanga.services

import com.notmanga.dao.Note
import com.notmanga.dto.NoteRequest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MockNoteServiceTest {
    val noteService = MockNoteService()
    val notes = setOf(
        Note{id = 1; title = "note1"; body = "body1"},
        Note{id = 2; title = "note2"; body = "body2"},
        Note{id = 3; title = "note3"; body = "body3"},
        Note{id = 4; title = "note4"; body = "body4"},
        Note{id = 5; title = "note5"; body = "body5"},
        Note{id = 6; title = "note6"; body = "body6"},
    )
    @Test
    fun createNote() {
        assertTrue(noteService.createNote(NoteRequest(title = "something", body = "also something")))
    }

    @Test
    fun findAllNotes() {
        assertEquals(notes, noteService.findAllNotes())
    }

    @Test
    fun findNoteById() {
        notes.forEachIndexed { index, note->
            assertEquals(note, noteService.findNoteById(index + 1))
        }
    }

    @Test
    fun updateNoteById() {
        var i = 1
        while (i<=6){
            assertEquals(true, noteService.updateNoteById(i, NoteRequest(title = "something", body = "also something")))
            i++
        }
        assertEquals(false, noteService.updateNoteById(i,NoteRequest(title = "something", body = "also something")))


    }

    @Test
    fun deleteNoteById() {
        var i = 1
        while (i<=6){
            assertEquals(true, noteService.deleteNoteById(i))
            i++
        }
        assertEquals(false, noteService.deleteNoteById(i))


    }
}