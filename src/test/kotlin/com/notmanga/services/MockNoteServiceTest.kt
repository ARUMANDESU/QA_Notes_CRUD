package com.notmanga.services

import com.notmanga.dto.NoteRequest
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MockNoteServiceTest {
    val noteService = MockNoteService()
    @Test
    fun createNote() {
        assertTrue(noteService.createNote(NoteRequest(title = "something", body = "also something")))
    }

    @Test
    fun findAllNotes() {
    }

    @Test
    fun findNoteById() {
    }

    @Test
    fun updateNoteById() {
    }

    @Test
    fun deleteNoteById() {
    }
}