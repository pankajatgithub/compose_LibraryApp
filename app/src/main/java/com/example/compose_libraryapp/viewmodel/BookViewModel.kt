package com.example.compose_libraryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_libraryapp.repository.Repository
import com.example.compose_libraryapp.room.BookEntity
import kotlinx.coroutines.launch

class BookViewModel(val repository: Repository) : ViewModel(){
    fun addBook(book:BookEntity){
        //repository has suspend function ,hence called inside scope function
        viewModelScope.launch {
            repository.addBookToRoom(book)
        }
    }

    val books = repository.getAllBooks()
}