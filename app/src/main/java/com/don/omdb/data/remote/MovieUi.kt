package com.don.omdb.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MovieUi(
    @field:SerializedName("loading") @field:Expose var loading: String = "",
    val id: String,
    val title: String,
    val year: String,
    val posterUrl: String?
)


fun MdlMovieList.toUi(): MovieUi = MovieUi(
    id = this.imdbID ?: this.Title ?: "", // adjust to your fields
    title = this.Title ?: "-",
    year = this.Year ?: "-",
    posterUrl = this.Poster
)