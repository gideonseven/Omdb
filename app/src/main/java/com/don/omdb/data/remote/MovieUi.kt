package com.don.omdb.data.remote


/**
 * Extension function to convert API model to UI model
 */
fun MdlMovieList.toUi(): MovieUi {
    return MovieUi(
        imdbID = this.imdbID ?: "",
        Title = this.Title ?: "Unknown",
        Year = this.Year ?: "",
        Poster = this.Poster ?: "",
        Type = this.Type ?: ""
    )
}

/**
 * UI model for displaying movies in Compose
 * This is a simple data class that's safe to use in UI
 */
data class MovieUi(
    val imdbID: String,
    val Title: String,
    val Year: String,
    val Poster: String,
    val Type: String
)