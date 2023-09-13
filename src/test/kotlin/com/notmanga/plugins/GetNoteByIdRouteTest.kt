package com.notmanga.plugins

import com.notmanga.services.MockNoteService
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class GetNoteByIdRouteTest {

    @Test
    fun testGetNoteWithId() = testApplication {
        application {
            configureHTTP()
            configureSerialization()
            notesRoutes(MockNoteService())
        }
        client.get("/note/1").apply {
            assertEquals("{\"id\":1,\"title\":\"note1\",\"body\":\"body1\"}", bodyAsText())
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @Test
    fun testNoteInvalidId() = testApplication {
        application {
            configureHTTP()
            configureSerialization()
            notesRoutes(MockNoteService())
        }
        client.get("/note/7").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
        }
        client.get("/note/d").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
        }

    }
}