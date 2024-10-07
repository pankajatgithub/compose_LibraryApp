package com.example.compose_libraryapp.room

import android.content.Context
import android.os.Build.VERSION
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version =1,
    exportSchema = false)

abstract class BooksDB : RoomDatabase() {

    abstract fun bookDao(): BookDAO

    //Companion Object - static object
    companion object{

        @Volatile //it's value never be cashed, hence real time change visible to all threads
        private var INSTANCE: BooksDB? = null

        //single instance for BookDB class
        fun getInstance(context: Context): BooksDB{

            //only one thread can enter at a time
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                      context.applicationContext,
                        BooksDB::class.java,
                        "books_db"
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}