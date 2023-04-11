package com.example.lectureexamples.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies

class AppViewModel : ViewModel() {
    private val _movies = getMovies().toMutableStateList()
    val movies: List<Movie>
        get() = _movies

    private val _favoriteMovies = mutableStateListOf<Movie>()
    val favoriteMovies: List<Movie>
        get() = _favoriteMovies

    fun onFavoritesClick(movie: Movie) {
        if(_favoriteMovies.contains(movie)) {
            _favoriteMovies.remove(movie)
        } else {
            _favoriteMovies.add(movie)
        }
    }

    fun isFavorite(movie: Movie): Boolean {
        return _favoriteMovies.contains(movie)
    }
    fun onAddMovie(movie: Movie) {
        _movies.add(movie)
    }
}