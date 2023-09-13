package com.notmanga.plugins

import com.notmanga.services.MockNoteService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class DeleteNoteByIdRouteTest {

    @Test
    fun testDeleteByValidId() = testApplication {
        application {
            configureSerialization()
            configureHTTP()
            notesRoutes(MockNoteService())
        }
        client.delete("/note/1").apply {
            assertEquals(HttpStatusCode.NoContent, status)
        }
    }

    @Test
    fun testDeleteByInvalidId() = testApplication {
        application {
            configureSerialization()
            configureHTTP()
            notesRoutes(MockNoteService())
        }
        client.delete("/note/7").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
        }
        client.delete("/note/t").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
        }
    }
}