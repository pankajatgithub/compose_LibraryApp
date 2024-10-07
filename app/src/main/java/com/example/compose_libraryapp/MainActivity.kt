package com.example.compose_libraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_libraryapp.ui.theme.Compose_LibraryAppTheme

//1. BookEntity - like table
//2.BookDao - for qurey
//3. BooksDB Database, and define entity array inside this

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_LibraryAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen()
                }
            }
        }
    }
}
@Composable
fun MainScreen(){
    Column {
OutlinedTextField(value ="",
    onValueChange ={},
    label = { Text(text = "Book Name")},
    placeholder = { Text(text = "Your Book Name")}
    )
        
        Button(onClick = {  }) {
            Text(text = "Insert Book into DB")
        }
    }
}
