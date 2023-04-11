package com.example.lectureexamples.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies


@Composable
fun FavoritesIcon(movie: Movie, isFavorite: Boolean = false, onFavoriteClicked: (Movie) -> Unit = {},) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
           horizontalAlignment = Alignment.End) {
        IconButton(
            modifier = Modifier.width(60.dp),
            onClick = { onFavoriteClicked(movie) }) {
            Icon(
                tint = MaterialTheme.colors.secondary,
                imageVector =
                if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Add to Favorites")
        }
    }
}
