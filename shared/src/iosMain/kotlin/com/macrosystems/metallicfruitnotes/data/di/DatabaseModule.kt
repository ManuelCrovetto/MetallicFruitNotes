package com.macrosystems.metallicfruitnotes.data.di

import com.macrosystems.metallicfruitnotes.data.local.DatabaseDriverFactory
import com.macrosystems.metallicfruitnotes.data.note.SqlDelightDataSource
import com.macrosystems.metallicfruitnotes.database.NoteDatabase
import com.macrosystems.metallicfruitnotes.domain.note.NoteDataSource

class DatabaseModule {
    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: NoteDataSource by lazy { SqlDelightDataSource(NoteDatabase(factory.createDriver())) }
}