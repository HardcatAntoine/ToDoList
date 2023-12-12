package com.example.todolist.di

import android.app.Application
import androidx.room.Room
import com.example.todolist.data.local.NodesDao
import com.example.todolist.data.local.NodesDatabase
import com.example.todolist.data.repository.NodesRepository
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
    fun providesDatabase(application: Application): NodesDatabase {
        return Room.databaseBuilder(
            application,
            NodesDatabase::class.java,
            "nodes"
        ).build()
    }

    @Provides
    fun providesNodesDao(database: NodesDatabase): NodesDao {
        return database.nodesDao()
    }

    @Provides
    fun provideNodesRepository(dao: NodesDao): NodesRepository {
        return NodesRepository(dao)
    }

}