package com.example.compose_libraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_libraryapp.repository.Repository
import com.example.compose_libraryapp.room.BookEntity
import com.example.compose_libraryapp.room.BooksDB
import com.example.compose_libraryapp.screens.UpdateScreen
import com.example.compose_libraryapp.ui.theme.Compose_LibraryAppTheme
import com.example.compose_libraryapp.viewmodel.BookViewModel

//1. BookEntity - like table
//2.BookDao - for qurey
//3. BooksDB Database, and define entity array inside this

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_LibraryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    val mContext = LocalContext.current
                    val db = BooksDB.getInstance(mContext)
                    val repository = Repository(db)
                    val myViewMddel = BookViewModel(repository = repository)
                    //navigation - install navigation dependency
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable("MainScreen") {
                            MainScreen(viewModel = myViewMddel, navController = navController)
                        }
                        composable("UpdateScreen/{bookId}"){
                            UpdateScreen(viewModel = myViewMddel,
                                bookId =it.arguments?.getString("bookId") )// it refers here navbackstack entry

                        }
                    }
//                    MainScreen(myViewMddel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: BookViewModel, navController: NavHostController) {
    var inputBook by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 30.dp, horizontal = 10.dp)
    ) {
        OutlinedTextField(value = inputBook,
            onValueChange = { enterdText ->
                inputBook = enterdText
            },
            label = { Text(text = "Book Name") },
            placeholder = { Text(text = "Your Book Name") }
        )

        Button(onClick = {
            //id will autogenerate even we send 0, it will not be considered
            viewModel.addBook(BookEntity(0, inputBook))
        }) {
            Text(text = "Insert Book into DB")
        }
        //The Book List
        BooksList(viewModel = viewModel,navController)
    }

}

@Composable
fun BookCard(viewModel: BookViewModel, book: BookEntity, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = "" + book.id, fontSize = 24.sp,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            )
            Text(text = book.title, fontSize = 24.sp)

            IconButton(onClick = { viewModel.deleteBook(book = book) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")

            }
            IconButton(onClick = {
                navController.navigate("UpdateScreen/${book.id}")
            }) {
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = "Edit")
            }

        }
    }
}

@Composable
fun BooksList(viewModel: BookViewModel, navController: NavHostController) {
    //whenever response is a flow, it will be collected as state , it will convert flow into state and give list inside the flow
    val books by viewModel.books.collectAsState(initial = emptyList())
    LazyColumn() {
        items(items = books) { item ->
            BookCard(viewModel = viewModel, book = item,navController)
        }
    }

}