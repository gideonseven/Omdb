package com.don.omdb.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.don.omdb.R
import com.don.omdb.data.remote.MovieDetailUi


@Composable
fun DetailScreen(
    movie: MovieDetailUi
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = stringResource(R.string.example),
            modifier = Modifier.size(width = 150.dp, height = 200.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = movie.title)
        movie.rating?.let { Text(text = it) }
        Text(text = movie.year)
        movie.description?.let { Text(text = it) }
    }
}


@Preview
@Composable
fun DetailScreenPreview() {
    val ui = MovieDetailUi(
        id = "tt0892769",
        title = "How to Train Your Dragon",
        year = "2010",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BMjA5NDQyMjc2NF5BMl5BanBnXkFtZTcwMjg5ODcyMw@@._V1_SX300.jpg",
        rating = "8.1",
        description = "THE DESCRIPTION"
    )
    DetailScreen(ui)
}