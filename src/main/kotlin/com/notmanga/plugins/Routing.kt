package com.notmanga.plugins

import com.notmanga.dao.Note
import com.notmanga.dto.ErrorResponse
import com.notmanga.dto.NoteRequest
import com.notmanga.dto.NoteResponse
import com.notmanga.services.INoteService
import com.notmanga.services.NoteService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.notesRoutes(noteService: INoteService = NoteService()) {
    routing {
        route("/note"){
            createNote(noteService)
            getAllNotesRoute(noteService)
            getNoteByIdRoute(noteService)
            updateNoteByIdRoute(noteService)
            deleteNoteByIdRoute(noteService)
        }

        /*route("/user"){
            registerUserRoute()
            loginUserRoute()
        }*/

    }
}

private fun Route.loginUserRoute() {
    TODO("Not yet implemented")
}

private fun Route.registerUserRoute() {
    TODO("Not yet implemented")
}

fun Route.deleteNoteByIdRoute(noteService: INoteService) {
    delete("/{id}") {
        val id: Int = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest,ErrorResponse("Invalid id"))

        val success =  noteService.deleteNoteById(id)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot delete book with id [$id]"))
    }

}

 fun Route.updateNoteByIdRoute(noteService: INoteService) {
    patch("/{id}") {
        val id: Int = call.parameters["id"]?.toIntOrNull() ?: return@patch call.respond(HttpStatusCode.BadRequest,ErrorResponse("Invalid id"))

        val request = call.receive<NoteRequest>()
        val success = noteService.updateNoteById(id, request)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot update book with id [$id]"))
    }
}

 fun Route.getNoteByIdRoute(noteService: INoteService) {
    get("/{id}") {
        val id: Int = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,ErrorResponse("Invalid id"))

        noteService.findNoteById(id)
            ?.let { foundNote -> foundNote.toNoteResponse() }
            ?.let { response -> call.respond(response) }
            ?: return@get call.respond(HttpStatusCode.BadRequest,ErrorResponse("Book with id [$id] not found"))
    }
}

 fun Note?.toNoteResponse(): NoteResponse? =
    this?.let { NoteResponse(it.id, it.title, it.body) }

 fun Route.getAllNotesRoute(noteService: INoteService) {
    get {
        val notes = noteService.findAllNotes().map ( Note::toNoteResponse )

        call.respond(notes)
    }
}

fun Route.createNote(noteService: INoteService) {
    post{
        val request = call.receive<NoteRequest>()
        val success = noteService.createNote(noteRequest = request)
        if (success)
            call.respond(HttpStatusCode.Created)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create book"))
    }
}

