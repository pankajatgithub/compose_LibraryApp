package com.example.compose_libraryapp.repository

import com.example.compose_libraryapp.room.BookEntity
import com.example.compose_libraryapp.room.BooksDB

class Repository (val booksDB: BooksDB) {

    suspend fun addBookToRoom(bookEntity: BookEntity){
        booksDB.bookDao().addBook(bookEntity)
    }

//    suspend fun addBookToServer()

    fun getAllBooks() = booksDB.bookDao().getAllBooks()
    suspend fun deleteBookFromRoom(bookEntity: BookEntity){
        booksDB.bookDao().deleteBook(bookEntity = bookEntity)
    }

}