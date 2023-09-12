package com.notmanga.plugins

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class GetAllNotesRouteTest {

    @Test
    fun testGet() = testApplication {
        application {
            notesRoutes()
        }
        client.get("/").apply {
            TODO("Please write your test here")
        }
    }
}