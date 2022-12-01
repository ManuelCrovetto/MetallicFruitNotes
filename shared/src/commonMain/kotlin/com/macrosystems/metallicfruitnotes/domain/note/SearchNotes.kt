package com.macrosystems.metallicfruitnotes.domain.note

import com.macrosystems.metallicfruitnotes.domain.time.DateTimeUtil

class SearchNotes {

    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank()) {
            return notes
        }
        return notes.filter { note ->
            note.title.trim().lowercase().contains(query.lowercase()) ||
                    note.content.trim().lowercase().contains(query.lowercase())
        }.sortedBy { DateTimeUtil.toEpochMillis(it.created) }
    }
}