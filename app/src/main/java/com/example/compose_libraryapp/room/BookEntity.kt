package com.example.compose_libraryapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//specify table name in bracket in table name is different from class
@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

   // @ColumnInfo(name = "book_title") - if column name id different from variable name
    val title: String
)
