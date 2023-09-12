package com.notmanga.dto

import kotlinx.serialization.Serializable

@Serializable
data class NoteRequest(
    val title: String,
    val body: String
)

@Serializable
data class NoteResponse(
    val id: Int,
    val title: String,
    val body: String
)


@Serializable
data class ErrorResponse(val message: String)
