package com.notmanga.plugins

import com.notmanga.services.MockNoteService
import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class CreateNoteTest {

    @Test
    fun testPostWithValidData() = testApplication {
        application {
            notesRoutes(MockNoteService())
        }
        client.post("/").apply {

        }
    }
}