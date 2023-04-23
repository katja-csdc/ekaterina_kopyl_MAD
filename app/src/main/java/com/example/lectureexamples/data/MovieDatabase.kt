package com.example.lectureexamples.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



//цей файл MovieDatabase є файлом підключення до бази даних, ініціалізацію і він є відповідальний за коннект до DB/SQLite
@Database(
    entities = [Movie::class], // tables in the db //zeigen Entities, mit welchen DB arbeitet -> hier Movie ::class
    version = 1, //schema version;
                 //whenever I change the schema I have to increase
                 //the version number
)

//create a DB instance -> S.4, p.33
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao //Dao instance so that the DB knows about the Dao
                                      //If I have multiple tables I have to add daos here

    //declare as singleton - companion objects are like static variables in Java
    companion object {

        @Volatile //never cache the value of Instance// специфіка роботи з пам'ятью
                  //cache - Puffer-Speicher, der Zugriff auf ein Hintergrundmedium möglich macht
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase{
            return instance ?: synchronized(this) { //wrap in synchronized block to prevent race conditions
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration() //if schema changes wipe/putzt the whole schema
                    .build()// create an instance of the db
                    .also{ instance = it }  // Instance = it -> override Instance with newly created db

            }
        }
    }

}