package com.example.lectureexamples.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.lectureexamples.MovieCard
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.viewmodels.AppViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavHostController, movieId: String, viewModel: AppViewModel) {
    val movie = viewModel.movies.first { it.id == movieId }

    Scaffold(
        topBar = { DetailScreenTopBar(navController, movie.title) }
    ) {
        Column {
            MovieCard(
                movie = movie,
                onMovieCardClick = { },
                onFavoritesClick = { viewModel.onFavoritesClick(movie) },
                isFavorite = { viewModel.isFavorite(movie) }
            )

            // image gallery
            DetailScreenContentLazyRow(movie)
        }
    }
}

@Composable
fun DetailScreenContentLazyRow(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .size(250.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = image,
                        builder = {
                            transformations(RoundedCornersTransformation(5f))
                        }
                    ),
                     contentDescription = "Movie Image",
                     modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

@Composable
fun DetailScreenTopBar(navController: NavHostController, movieTitle: String) {
    TopAppBar(
        title = { Text(text = movieTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "More")
            }
        }
    )
}
