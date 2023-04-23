package com.example.lectureexamples.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lectureexamples.viewmodels.MovieAppViewModel


//instantiate a ViewModel with parameters -> S.5p.38
class MovieAppViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieAppViewModel::class.java)) {
            return MovieAppViewModel(repository = repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}