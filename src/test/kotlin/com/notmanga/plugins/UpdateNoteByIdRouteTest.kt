package com.notmanga.plugins

import com.notmanga.dto.NoteRequest
import com.notmanga.services.MockNoteService
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import junit.framework.TestCase
import kotlin.test.Test

class UpdateNoteByIdRouteTest {

    @Test
    fun testPatchIdValid() = testApplication {
        application {
            configureHTTP()
            configureSerialization()
            notesRoutes(MockNoteService())
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        client.patch("/note/1"){
            contentType(ContentType.Application.Json)
            setBody(NoteRequest("my note", "something"))
        }.apply {
            TestCase.assertEquals(HttpStatusCode.NoContent, status)
        }
    }

    @Test
    fun testPatchIdInvalidId() = testApplication {
        application {
            configureHTTP()
            configureSerialization()
            notesRoutes(MockNoteService())
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        client.patch("/note/7"){
            contentType(ContentType.Application.Json)
            setBody(NoteRequest("my note", "something"))
        }.apply {
            TestCase.assertEquals(HttpStatusCode.BadRequest, status)
        }

        client.patch("/note/d"){
            contentType(ContentType.Application.Json)
            setBody(NoteRequest("my note", "something"))
        }.apply {
            TestCase.assertEquals(HttpStatusCode.BadRequest, status)
        }
    }
}