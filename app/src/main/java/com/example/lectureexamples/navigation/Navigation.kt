package com.example.lectureexamples.navigation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lectureexamples.data.MovieAppViewModelFactory
import com.example.lectureexamples.data.MovieDatabase
import com.example.lectureexamples.data.MovieRepository
import com.example.lectureexamples.screens.AddMovieScreen
import com.example.lectureexamples.screens.DetailScreen
import com.example.lectureexamples.screens.FavoritesScreen
import com.example.lectureexamples.screens.HomeScreen
import com.example.lectureexamples.viewmodels.MovieAppViewModel

@Composable
fun MyNavigation(): Unit {

    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = MovieAppViewModelFactory(repository = repository)


    val viewModel = viewModel<MovieAppViewModel>(factory = factory) // damit ViewModel Repository erstellen könnte
                                                                   //übergeben wir ihr Factory(Fabrik, die uns Repository erstellt)



    val navController: NavHostController = rememberNavController() // 1) creating NavController instance


    NavHost(
        navController = navController,  // 2) passing the navController instance
        startDestination = "HomeScreen" // 2) passing a start destination
    ) {
        composable("HomeScreen") { HomeScreen(navController, viewModel) }                // 3) creating a NavGraph by defining a destination
        composable("AddMovieScreen") { AddMovieScreen(navController, viewModel) }        // 3) creating a NavGraph by defining a destination
        composable("FavoriteScreen") { FavoritesScreen(navController, viewModel) }       // 3) creating a NavGraph by defining a destination
        composable(
            route = "DetailScreen/{movieId}",
            arguments = listOf(
                navArgument("movieId") {type = NavType.StringType}
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            DetailScreen(navController, movieId, viewModel)
        }
        composable(
            route = "TestRoute",
            content = { TestRouteComposable() }
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestRouteComposable() {
    Scaffold() {
        Text(text = "ABC")
    }
}
