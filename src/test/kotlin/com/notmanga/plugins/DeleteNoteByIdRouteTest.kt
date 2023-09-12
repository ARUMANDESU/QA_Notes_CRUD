package com.notmanga.plugins

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class DeleteNoteByIdRouteTest {

    @Test
    fun testDeleteId() = testApplication {
        application {
            notesRoutes()
        }
        client.delete("/{id}").apply {
            TODO("Please write your test here")
        }
    }
}