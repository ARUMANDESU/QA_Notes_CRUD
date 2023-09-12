package com.notmanga.plugins

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class GetNoteByIdRouteTest {

    @Test
    fun testGetId() = testApplication {
        application {
            notesRoutes()
        }
        client.get("/{id}").apply {
            TODO("Please write your test here")
        }
    }
}