package com.example.lectureexamples.screens

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavViewModelStoreProvider
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.lectureexamples.MovieCard
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavHostController, movieId: String) {
    val movie = getMovies().first { it.id == movieId }

    Scaffold(
        topBar = { DetailScreenTopBar(navController, movie.title) }
    ) {
        MovieCard(
            navController = navController,
            movie = movie,
            isDetailScreen = true)

        //  add new composable function that show image gallery, e.g. ImageGallery()
@Composable
fun DetailScreenContentLazyRow(movie: Movie = getMovies() [0]) {
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
                //DetailScreenContentLazyRow(movie = movie)
            }
        }
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
