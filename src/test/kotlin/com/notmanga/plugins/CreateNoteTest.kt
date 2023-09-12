package com.notmanga.plugins

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class CreateNoteTest {

    @Test
    fun testPost() = testApplication {
        application {
            notesRoutes()
        }
        client.post("/").apply {
            TODO("Please write your test here")
        }
    }
}