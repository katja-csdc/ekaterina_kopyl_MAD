package com.example.lectureexamples.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lectureexamples.data.Movie
import com.example.lectureexamples.data.MovieRepository
//import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies
import kotlinx.coroutines.launch


// inherit from ViewModel class
class MovieAppViewModel(
    private val repository: MovieRepository  //передаємо сюди репозиторій, щоб вона могла з DAO взаємодіяти -> S.5p.36
) : ViewModel() {

    init {
        viewModelScope.launch { //функція з Coroutines(використовує окрему бібліотеку) ->
                 //trigger the flow and consume its elements using collect
                //predefined CoroutineScope included in ViewModel KTX extension
               //do some asynchronous request, eg.call a db operation or network call
            repository.getAll().collect { movies ->  //wir nehmen alle Filme aus DB
                _movies.clear() //List Movie cleared
                _movies.addAll(movies) } //und danach alle Filme eingefügt, die in DB sind. Gefresht MovieList -> Recomposition

            }
        }
    }


    private val _movies = getMovies().toMutableStateList() // get all movies and create an observable list
    val movies: List<Movie> // expose previously created list but immutable
        get() = _movies

    //keep state of favorite movies
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
