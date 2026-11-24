package com.example.mars_kilaa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mars_kilaa.data.dao.NoteDao
import com.example.mars_kilaa.data.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false) // Naikkan version jika skema berubah
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Menghapus DB lama & membuat ulang DB baru saat skema berubah
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}