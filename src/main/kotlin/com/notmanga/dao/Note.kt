package com.notmanga.dao

import kotlinx.serialization.Serializable
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar


interface Note: Entity<Note>{
    @Serializable
    companion object: Entity.Factory<Note>()

    var id: Int
    var title: String
    var body: String
}
object Notes: Table<Note>("t_note") {
    val id = int("id").primaryKey().bindTo (Note::id )
    val title = varchar("title").bindTo(Note::title)
    val body = varchar("body").bindTo(Note::body)
}