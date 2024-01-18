package com.example.todolist.di

import android.app.Application
import androidx.room.Room
import com.example.todolist.data.local.NotesDao
import com.example.todolist.data.local.NotesDatabase
import com.example.todolist.data.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(application: Application): NotesDatabase {
        return Room.databaseBuilder(
            application,
            NotesDatabase::class.java,
            "notes"
        ).build()
    }

    @Provides
    fun providesNotesDao(database: NotesDatabase): NotesDao {
        return database.notesDao()
    }

    @Provides
    fun provideNotesRepository(dao: NotesDao): NotesRepository {
        return NotesRepository(dao)
    }

}