package com.example.todolist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Nodes::class], version = 1)
abstract class NodesDatabase : RoomDatabase() {
    abstract fun nodesDao(): NodesDao


    companion object {
        @Volatile
        private var INSTANCE: NodesDatabase? = null

        fun getDatabase(context: Context): NodesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    NodesDatabase::class.java,
                    "favorites"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}