package com.example.compose_libraryapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.compose_libraryapp.room.BookEntity
import com.example.compose_libraryapp.viewmodel.BookViewModel

@Composable
fun UpdateScreen(viewModel: BookViewModel, bookId: String?) {
    Column() {
        var inputBook by remember {
            mutableStateOf("")
        }

        OutlinedTextField(value = inputBook,
            onValueChange = { enteredValue -> inputBook = enteredValue },
            label = { Text(text = "Update Book Name") },
            placeholder = { Text(text = "New Book Name") }
        )
        Button(onClick = {
            var newBook = BookEntity(bookId!!.toInt(),inputBook)
           viewModel.updateBook(newBook)

        }) {
            Text(text = "Update Book")
        }
    }
}