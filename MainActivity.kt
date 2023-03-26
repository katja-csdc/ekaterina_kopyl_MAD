package com.example.lectureexamples

import android.annotation.SuppressLint
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
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.navigation.MyNavigation
import com.example.lectureexamples.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                MyNavigation()

            }
        }
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
fun MovieCard(navController: NavHostController, movie: Movie, isDetailScreen: Boolean = false) {
    var showExpandedMenu by remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            if (!isDetailScreen) {
                navController.navigate("DetailScreen/${movie.id}")
            }
        },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 10.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
            ) {
                val painter = rememberImagePainter(
                    data = movie.images.first(),
                )
                Image(
//                    painter = painterResource(id = R.drawable.avatar2),
                    painter = painter,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop
                )
                if (painter.state is ImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd,
                ){
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
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
