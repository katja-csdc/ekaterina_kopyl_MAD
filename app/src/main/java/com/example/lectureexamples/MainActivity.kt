package com.example.lectureexamples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.navigation.MyNavigation
import com.example.lectureexamples.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ACTIVITY", "onCreate: ")

        //Use your created navigation composable in MainActivity
        //This will start the application with previously defined startDestination in NavHost
        setContent {
            MyApp {
                MyNavigation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ACTIVITY", "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ACTIVITY", "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ACTIVITY", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ACTIVITY", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ACTIVITY", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ACTIVITY", "onDestroy: ")
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MovieAppTheme {
        content()
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieCard(
    movie: Movie,
    onMovieCardClick: () -> Unit,
    isFavorite: (movie: Movie) -> Boolean,
    onFavoritesClick: (movie: Movie) -> Unit

) {
    var showExpandedMenu by remember { mutableStateOf(false) }
    var favoriteState = isFavorite(movie)

     Card(modifier = Modifier
         .fillMaxWidth()
         .padding(5.dp)
         .clickable { onMovieCardClick() },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 10.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
            ) {
                if(movie.images.isNotEmpty()) {
                    val painter = rememberImagePainter(
                        data = movie.images.first(),
                    )
                    Image(
                        painter = painter,
                        contentDescription = "Movie Poster",
                        contentScale = ContentScale.Crop
                    )
                    if (painter.state is ImagePainter.State.Loading) {
                        CircularProgressIndicator()
                    }
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd,
                ){
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { onFavoritesClick(movie) },
                        tint = MaterialTheme.colors.secondary,
                        imageVector = if (favoriteState) {
                            Icons.Default.Favorite
                        } else {Icons.Default.FavoriteBorder
                               },
                        contentDescription = "Add to favorites",
                    )
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)
                //To toggle the arrow icon, you can use animations (turn the arrow) or switch
                //the ImageVector from KeyboardArrowDown to KeyboardArrowUp.
                Icon(
                    imageVector =
                    if (showExpandedMenu) Icons.Outlined.KeyboardArrowDown
                    else Icons.Rounded.KeyboardArrowUp,
                    contentDescription = "Show details",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            showExpandedMenu = !showExpandedMenu
                        },
                )

            }

            MovieCardExpandedContent(showExpandedMenu, movie)
        }
    }
}

@Composable
//Hints: Use AnimatedVisibility or animateContentSize to collapse the details.
fun MovieCardExpandedContent(visible: Boolean = true, movie: Movie) {
    AnimatedVisibility(
        visible = visible,
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Director: ${movie.director}")
            Text(text = "Released: ${movie.year}")
            Text(text = "Genre: ${movie.genre}")
            Text(text = "Rating: ${movie.rating}")
            Text(text = "Actors: ${movie.actors}")

            Divider()
            Row {
                Text(text = movie.plot,
                    textAlign = TextAlign.Center )
            }
        }
    }
}
