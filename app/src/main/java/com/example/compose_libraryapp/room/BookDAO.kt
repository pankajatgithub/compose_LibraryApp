package com.example.compose_libraryapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDAO {

    @Insert
    suspend fun addBook(bookEntity: BookEntity)

//    @Query("SELECT * FROM BookEntity WHERE title LIKE 'P%'") //ALL TITLE STARTING WITH P

    @Query("SELECT * FROM BookEntity ORDER BY id DESC")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Delete
    suspend fun deleteBook(bookEntity: BookEntity)

    @Update
    suspend fun updateBook(bookEntity: BookEntity)

}