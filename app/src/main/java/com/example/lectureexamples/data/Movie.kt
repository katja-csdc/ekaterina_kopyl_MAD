package com.example.lectureexamples.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


//Adapt your Movie data class to be used as a Room entity
@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String?,
    val year: String?,
    val genre: String?,
    val director: String?,
    val actors: String?,
    val plot: String?,
    // because we need special converter to convert list of string to string
    @Ignore
    val images: List<String>?,
    val rating: String?,
    val favorite: Boolean = false
)
