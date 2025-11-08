package com.don.omdb.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetailUi(
    @field:SerializedName("loading") @field:Expose var loading: String = "",
    val id: String,
    val title: String,
    val year: String,
    val posterUrl: String?,
    val rating: String?,
    val description: String?
)


fun MdlDetail.toUi(): MovieDetailUi = MovieDetailUi(
    id = imdbID ?: title ?: "", // adjust to your fields
    title = title ?: "-",
    year = year ?: "-",
    posterUrl = poster ?: "-",
    rating = imdbRating,
    description = plot
)