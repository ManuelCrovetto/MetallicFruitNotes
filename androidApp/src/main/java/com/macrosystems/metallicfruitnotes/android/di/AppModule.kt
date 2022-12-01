package com.macrosystems.metallicfruitnotes.android.di

import android.app.Application
import com.macrosystems.metallicfruitnotes.data.local.DatabaseDriverFactory
import com.macrosystems.metallicfruitnotes.data.note.SqlDelightDataSource
import com.macrosystems.metallicfruitnotes.database.NoteDatabase
import com.macrosystems.metallicfruitnotes.domain.note.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SqlDelightDataSource(NoteDatabase(driver))
    }
}