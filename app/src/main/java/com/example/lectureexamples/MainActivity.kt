package com.example.lectureexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {  // ich erstelle hier Content vom UI
            MovieAppTheme { // just setting theme
                Scaffold(
                    topBar = { TopBar() }
                ) //ich rufe da die Funktion TopBar auf
                {
                    it
                    MovieList()
                }
            }
        }
    }
}
@Preview
@Composable
fun TopBar() {
    //Use a remember state variable to toggle the
    //DropDownMenu when the IconButton from the AppBar is clicked.
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "Movies") },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
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
            }
        },
    )
}

@Preview
@Composable
fun MovieList(movies: List<Movie> = getMovies()){
    LazyColumn {
        items(movies) {movie ->
            MovieCard(movie)
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    var showExpandedMenu by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
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
                    .padding(10.dp)
                    .clickable { },
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
                Text("${movie.title}", style = MaterialTheme.typography.h6)
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

            MovieExpandedContent(showExpandedMenu, movie)
        }
    }
}

@Composable
//Hints: Use AnimatedVisibility or animateContentSize to collapse the details.
fun MovieExpandedContent(visible: Boolean = true, movie: Movie) {
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