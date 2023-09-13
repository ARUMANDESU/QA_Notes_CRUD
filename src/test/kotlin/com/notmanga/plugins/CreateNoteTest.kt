package com.notmanga.plugins

import com.notmanga.dto.NoteRequest
import com.notmanga.services.MockNoteService
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class CreateNoteTest {

    @Test
    fun testPostWithValidData() = testApplication {
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

        client.post("/note"){
            contentType(ContentType.Application.Json)
            setBody(NoteRequest("my note", "something"))
        }.apply {
           assertEquals(HttpStatusCode.Created, status)
        }
    }

    @Test
    fun testPostWithInValidData() = testApplication {
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

        client.post("/note"){
            contentType(ContentType.Application.Json)
            setBody("{}")
        }.apply {
           assertEquals(HttpStatusCode.BadRequest, status)
        }
    }


}