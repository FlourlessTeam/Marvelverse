package com.example.marvelverse.domain.entities.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvelverse.domain.entities.dao.ComicDao
import com.example.marvelverse.domain.entities.main.Comic

@Database(entities = [Comic::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
 abstract val comicDao:ComicDao

 companion object{
     fun getDatabase(context: Context) {
         val db =Room.databaseBuilder(context,AppDatabase::class.java,"Comic")
     }
 }
}
