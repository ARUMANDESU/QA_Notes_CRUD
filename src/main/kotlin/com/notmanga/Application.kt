package com.notmanga

import com.notmanga.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 5000, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureHTTP()
    configureRouting()
}
