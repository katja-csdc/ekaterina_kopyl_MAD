package com.example.lectureexamples.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*
DAOs include methods that offer abstract access to my DB
2 types of methods:
convenience methods -> for insert, update and delete operations (no SQL needed)
query methods -> le me write my own SQL query to interact with DB
 */


//Add a MovieDao with following functions
interface MovieDao {
    @Insert
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Update
    fun update(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE favorite = 1")
    /*SQLite does not have a separate Boolean storage class
    Instead, Boolean values are stored as Integers 0(false) and 1(true)
    Library Room "übersetzt" Nuller und Einser  von DB in false/true
    */
    fun getAllFavorite(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getById(id: Int): Flow<Movie>

}