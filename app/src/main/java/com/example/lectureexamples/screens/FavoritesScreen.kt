package com.example.lectureexamples.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lectureexamples.MovieCard
import com.example.lectureexamples.navigation.MovieScreens
import com.example.lectureexamples.viewmodels.AppViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(navController: NavHostController, viewModel: AppViewModel) {
    Scaffold(
        topBar = {
            TopAppBar {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "My Favorites Movies", style = MaterialTheme.typography.h6)
                }
            }
        }) {
        LazyColumn {
            items(viewModel.favoriteMovies) { movie ->
                MovieCard(
                    movie = movie,
                    onMovieCardClick = { navController.navigate(route = MovieScreens.DetailScreen.name + "/${movie.id}") },
                    onFavoritesClick = { viewModel.onFavoritesClick(movie) },
                    isFavorite = { viewModel.isFavorite(movie) }
                )
            }
        }
    }
}



