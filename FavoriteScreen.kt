package com.example.lectureexamples.screens

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lectureexamples.MovieCardExpandedContent
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.navigation.MovieScreens

/*@Composable
fun FavoriteScreen(navController: NavController = rememberNavController()) {
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
        })
} */


