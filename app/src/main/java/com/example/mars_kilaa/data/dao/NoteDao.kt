package com.example.mars_kilaa.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mars_kilaa.data.entity.NoteEntity

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    suspend fun getAll(): List<NoteEntity>

    @Delete
    suspend fun delete(note: NoteEntity)
}