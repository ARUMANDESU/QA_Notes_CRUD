package com.notmanga.plugins

import com.notmanga.dto.NoteResponse
import com.notmanga.services.MockNoteService
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class GetAllNotesRouteTest {

    @Test
    fun testGetAllNotes() = testApplication {
        application {
            configureHTTP()
            notesRoutes(MockNoteService())
            configureSerialization()
        }

        val expectedAnsw = setOf(
            NoteResponse( 1,"note1",  "body1"),
            NoteResponse( 2,"note2",  "body2"),
            NoteResponse( 3,"note3",  "body3"),
            NoteResponse( 4,"note4",  "body4"),
            NoteResponse( 5,"note5",  "body5"),
            NoteResponse( 6,"note6",  "body6"),
        )
        val expectedJson = Json.encodeToString(expectedAnsw)

        client.get("/note").apply {
            assertEquals(expectedJson,bodyAsText())
        }
    }
}