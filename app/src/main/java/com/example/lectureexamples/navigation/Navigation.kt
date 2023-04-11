package com.example.lectureexamples.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lectureexamples.screens.AddMovieScreen
import com.example.lectureexamples.screens.DetailScreen
import com.example.lectureexamples.screens.FavoritesScreen
import com.example.lectureexamples.screens.HomeScreen
import com.example.lectureexamples.viewmodels.AppViewModel

@Composable
fun MyNavigation(): Unit {
    val navController: NavHostController = rememberNavController() //creating NavController instance
    val viewModel = viewModel<AppViewModel>()
    for (movie in viewModel.movies) {
        println(movie)
    }
    NavHost(
        navController = navController,
        startDestination = "HomeScreen"
    ) {
        composable("HomeScreen") { HomeScreen(navController, viewModel) }
        composable("AddMovieScreen") { AddMovieScreen(navController, viewModel) }
        composable("FavoriteScreen") { FavoritesScreen(navController, viewModel) }
        composable(
            "DetailScreen/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(navController, viewModel = viewModel, movieId = backStackEntry.arguments?.getString("movieId") ?: "")
        }
    }
}