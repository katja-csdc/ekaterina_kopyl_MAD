package com.example.lectureexamples.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lectureexamples.MovieCard
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.navigation.MovieScreens
import com.example.lectureexamples.viewmodels.AppViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController, viewModel: AppViewModel) {
    Scaffold(
        topBar = { HomeScreenTopBar(navController) }
    ) { //ich rufe da die Funktion TopBar auf
        MovieList(navController, viewModel.movies, viewModel)
    }
}


@Composable
fun HomeScreenTopBar(navController: NavHostController) {
    //Use a remember state variable to toggle the
    //DropDownMenu when the IconButton from the AppBar is clicked.
    var showDropdownMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "Movies") },
        actions = {
            IconButton(onClick = { showDropdownMenu = !showDropdownMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(expanded = showDropdownMenu, onDismissRequest = { showDropdownMenu = false }) {
                DropdownMenuItem(onClick = {navController.navigate("FavoriteScreen") }) {
                    Row {
                        Icon(imageVector = Icons.Default.Favorite,
                            contentDescription = "my favorites",
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(text = "Favorites",
                            modifier = Modifier
                                .padding(4.dp)
                                .width(100.dp)
                        )
                    }
                }
                DropdownMenuItem(onClick = {navController.navigate("AddMovieScreen") }) {
                    Row {
                        Icon(imageVector = Icons.Default.AddCircle,
                            contentDescription = "add a new movie",
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(text = "Add a new Movie",
                            modifier = Modifier
                                .padding(4.dp)
                                .width(100.dp)
                        )
                    }
                }
            }
        },
    )
}

@Composable
fun MovieList(navController: NavHostController,
              movieList: List<Movie>,
              viewModel: AppViewModel
){
    LazyColumn {
        items(movieList) {movie ->
            MovieCard(
                movie = movie,
                onMovieCardClick = { navController.navigate(route = MovieScreens.DetailScreen.name + "/${movie.id}")},
                onFavoritesClick = { viewModel.onFavoritesClick(movie) },
                isFavorite = { viewModel.isFavorite(movie) }
            )
        }
    }
}
