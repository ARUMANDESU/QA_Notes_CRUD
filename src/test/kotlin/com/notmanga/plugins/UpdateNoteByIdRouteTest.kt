package com.notmanga.plugins

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class UpdateNoteByIdRouteTest {

    @Test
    fun testPatchId() = testApplication {
        application {
            notesRoutes()
        }
        client.patch("/{id}").apply {
            TODO("Please write your test here")
        }
    }
}