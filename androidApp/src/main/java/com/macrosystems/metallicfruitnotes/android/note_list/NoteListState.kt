package com.macrosystems.metallicfruitnotes.android.note_list

import com.macrosystems.metallicfruitnotes.domain.note.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)